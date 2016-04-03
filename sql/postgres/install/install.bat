SET server=localhost
SET /P server="Server [%server%]: "

SET port=5432
SET /P port="Port [%port%]: "

SET database=xysvr2
SET /P database="Database [%database%]: "

SET username=postgres
SET /P username="Username [%username%]: "

SET PGCLIENTENCODING=UTF8

REM run psql
psql -h %server% -p %port% -d %database% -U %username% -W -f install.sql

pause
