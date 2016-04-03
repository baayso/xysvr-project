/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.dao.mongo;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.ReflectionUtils;

import cn.xyspace.xysvr.common.core.entity.IdEntity;

import com.mongodb.Mongo;
import com.mongodb.connection.Cluster;
import com.mongodb.connection.ClusterDescription;
import com.mongodb.connection.ClusterType;
import com.mongodb.connection.ServerConnectionState;
import com.mongodb.connection.ServerDescription;

/**
 * {@linkplain IBaseMongoDao} 接口的默认实现。
 * 
 * @author ChenFangjie(2015年3月4日 下午2:13:19)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class BaseMongoDaoImpl<T extends IdEntity> implements IBaseMongoDao<T> {

    @Inject
    protected MongoTemplate mongoTemplate;

    @Inject
    private Mongo mongo;

    protected Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public BaseMongoDaoImpl() {
        // 通过反射获取子类类型以确定T的类型
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) type.getActualTypeArguments()[0];
    }

    @Override
    public boolean isMongoConnected() {
        boolean connected = true;

        Method getCluster = ReflectionUtils.findMethod(Mongo.class, "getCluster");

        // 关闭安全检查以提高反射性能
        if (!getCluster.isAccessible()) {
            getCluster.setAccessible(true);
        }

        Cluster mongoCluster = (Cluster) ReflectionUtils.invokeMethod(getCluster, this.mongo);

        ClusterDescription clusterDesc = mongoCluster.getDescription();
        ClusterType clusterType = clusterDesc.getType();

        if (ClusterType.STANDALONE.equals(clusterType)) {
            Set<ServerDescription> allServer = clusterDesc.getAll();
            for (ServerDescription svrDesc : allServer) {
                ServerConnectionState state = svrDesc.getState();
                connected = ServerConnectionState.CONNECTED.equals(state);
            }
        }
        else if (ClusterType.UNKNOWN.equals(clusterType)) {
            connected = false;
        }

        return connected;
    }

    @Override
    public void insert(T entity) {
        this.mongoTemplate.insert(entity);
    }

    @Override
    public void delete(String id) {
        this.mongoTemplate.remove(new Query(Criteria.where("id").is(id)), this.entityClass);
    }

    // @Override
    // public WriteResult updateObject(String id, String name) {
    // return this.mongoTemplate.updateFirst(new Query(Criteria.where("id").is(id)), Update.update("name", name), this.entityClass);
    // }

    @Override
    public T get(String id) {
        return this.mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), this.entityClass);
    }

    @Override
    public List<T> getAll() {
        return this.mongoTemplate.findAll(this.entityClass);
    }

    @Override
    public void createCollection() {
        if (!this.mongoTemplate.collectionExists(this.entityClass)) {
            this.mongoTemplate.createCollection(this.entityClass);
        }
    }

    @Override
    public void dropCollection() {
        if (this.mongoTemplate.collectionExists(this.entityClass)) {
            this.mongoTemplate.dropCollection(this.entityClass);
        }
    }

}
