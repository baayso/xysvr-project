package cn.xyspace.xysvr.common.dao;

import java.util.Map;

import cn.xyspace.xysvr.common.core.dao.annotation.MyBatisRepository;
import cn.xyspace.xysvr.common.entity.User;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口，动态在Spring Context中生成实现。 <br />
 * 方法名称必须与user2-mapper.xml中保持一致。
 * 
 * @author ChenFangjie(2014/11/30 13:52:08)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@MyBatisRepository
public interface IUserMybatisDao {

    public int insert(User entity);

    public User select(Map<String, Object> parameters);

    public User selectById(String id);

}
