-- 更新用户等级触发函数
CREATE OR REPLACE FUNCTION trigger_func_update_user_level() RETURNS TRIGGER AS
	$BODY$
		DECLARE
			new_level SMALLINT := 1;
		BEGIN
			IF (TG_OP != 'UPDATE') THEN
				RETURN NULL;
			ELSEIF (NEW.bonus_point = OLD.bonus_point) THEN
				RETURN NULL;
			END IF;

			IF (NEW.bonus_point < 100) THEN
				new_level := 1;
			ELSEIF (NEW.bonus_point < 300) THEN 
				new_level := 2;
			ELSEIF (NEW.bonus_point < 600) THEN 
				new_level := 3;
			ELSEIF (NEW.bonus_point < 1000) THEN 
				new_level := 4;
			ELSEIF (NEW.bonus_point < 1500) THEN 
				new_level := 5;
			ELSEIF (NEW.bonus_point < 2100) THEN 
				new_level := 6;
			ELSEIF (NEW.bonus_point < 2800) THEN 
				new_level := 7;
			ELSEIF (NEW.bonus_point < 3600) THEN 
				new_level := 8;
			ELSEIF (NEW.bonus_point < 4500) THEN 
				new_level := 9;
			ELSEIF (NEW.bonus_point < 5500) THEN 
				new_level := 10;
			ELSEIF (NEW.bonus_point < 6600) THEN 
				new_level := 11;
			ELSEIF (NEW.bonus_point < 7800) THEN 
				new_level := 12;
			ELSEIF (NEW.bonus_point < 9100) THEN 
				new_level := 13;
			ELSEIF (NEW.bonus_point < 10500) THEN 
				new_level := 14;
			ELSEIF (NEW.bonus_point < 12000) THEN 
				new_level := 15;
			ELSEIF (NEW.bonus_point < 13600) THEN 
				new_level := 16;
			ELSEIF (NEW.bonus_point < 15300) THEN 
				new_level := 17;
			ELSEIF (NEW.bonus_point >= 15300) THEN 
				new_level := 18;
			END IF;

			IF (new_level != NEW.level) THEN
				UPDATE t_user_asset SET level = new_level WHERE id = NEW.id;
				RETURN NEW;
			END IF;

	        RETURN NULL;
		END;
	$BODY$
LANGUAGE 'plpgsql';


-- 更新用户等级触发器
DROP TRIGGER IF EXISTS trigger_update_user_level ON t_user_asset;
CREATE TRIGGER trigger_update_user_level AFTER UPDATE OF bonus_point ON t_user_asset
	FOR EACH ROW EXECUTE PROCEDURE trigger_func_update_user_level();

