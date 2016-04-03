/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.common.core.dao.datasource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.jdbc.datasource.lookup.DataSourceLookup;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

/**
 * 动态数据源，实现基于多数据源的读写分离、主备切换、故障转移、恢复检测和负载均衡。
 * 
 * <p>
 * 源于 http://git.oschina.net/uncode/uncode-dal-all/blob/master/uncode-dal/src/main/java/cn/uncode/dal/datasource/DynamicDataSource.java
 *
 * @author ChenFangjie(2015年2月7日 下午4:33:06)
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
public class DynamicDataSource extends AbstractDataSource implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

    private long checkTimeInterval = 10000;

    private ConcurrentLinkedQueue<Object> disconnectDataSources = new ConcurrentLinkedQueue<Object>();

    private DataSourceLookup dataSourceLookup = new JndiDataSourceLookup();

    private String checkAvailableSql = "select 1";

    private AtomicInteger lock = new AtomicInteger(0);

    private Map<Object, Object> slaveDataSources;

    private Map<Object, DataSource> resolvedSlaveDataSources;

    private Object masterDataSource;

    private Object standbyDataSource;

    private DataSource resolvedMasterDataSource;

    private DataSource resolvedStandbyDataSource;

    private DataSource currentDataSource;

    /**
     * 
     * @param slaveDataSources
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public void setSlaveDataSources(Map<Object, Object> slaveDataSources) {
        this.slaveDataSources = slaveDataSources;
    }

    /**
     * 
     * @param dataSourceLookup
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public void setDataSourceLookup(DataSourceLookup dataSourceLookup) {
        this.dataSourceLookup = (dataSourceLookup != null ? dataSourceLookup : new JndiDataSourceLookup());
    }

    /**
     * 
     * @param masterDataSource
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public void setMasterDataSource(Object masterDataSource) {
        this.masterDataSource = masterDataSource;
    }

    /**
     * 
     * @param standbyDataSource
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public void setStandbyDataSource(Object standbyDataSource) {
        this.standbyDataSource = standbyDataSource;
    }

    @Override
    public void afterPropertiesSet() {
        if (this.slaveDataSources == null) {
            throw new IllegalArgumentException("Property 'slaveDataSources' is required");
        }

        this.resolvedSlaveDataSources = new HashMap<Object, DataSource>(this.slaveDataSources.size());

        for (Map.Entry<Object, Object> entry : this.slaveDataSources.entrySet()) {
            DataSource dataSource = this.resolveSpecifiedDataSource(entry.getValue());
            this.resolvedSlaveDataSources.put(entry.getKey(), dataSource);
        }

        if (this.masterDataSource == null) {
            throw new IllegalArgumentException("Property 'masterDataSource' is required");
        }

        if (this.standbyDataSource != null) {
            this.resolvedStandbyDataSource = this.resolveSpecifiedDataSource(this.standbyDataSource);
        }

        this.resolvedMasterDataSource = this.resolveSpecifiedDataSource(this.masterDataSource);

        Thread thread = new CheckDataSourceDaemonThread();
        thread.start();
    }

    /**
     * 确定当前的查找键。
     * 
     * @return
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    protected Object determineCurrentLookupKey() {
        DBType lookupKey = DBContextHolder.getCurrentDBType();

        if (lookupKey == null) {
            logger.debug("none routing key, choose defaultDataSource for current connection");
        }
        else {
            logger.debug("choose dataSource for current connection by routing key " + lookupKey);
        }

        return lookupKey;
    }

    @Override
    public Connection getConnection() throws SQLException {
        Object lookupKey = this.determineCurrentLookupKey();
        Object dataSourceKey = null;
        DataSource dataSource = null;

        if (DBType.SLAVE.equals(lookupKey)) {
            if (!this.resolvedSlaveDataSources.isEmpty()) {
                int size = this.resolvedSlaveDataSources.size();
                int index = 0;
                int targetIndex = 0;

                if (size > 1) {
                    targetIndex = RANDOM.nextInt(size);
                }

                for (Map.Entry<Object, DataSource> entry : this.resolvedSlaveDataSources.entrySet()) {
                    if (index == targetIndex) {
                        dataSourceKey = entry.getKey();
                        dataSource = entry.getValue();
                        break;
                    }

                    index++;
                }
            }
            else {
                logger.debug("Resolved slave data source is empty.");
            }
        }

        if (dataSource == null) {
            dataSourceKey = MASTER_DATASOURCE_KEY;
            dataSource = this.getCurrentDataSource();
        }

        if (dataSource == null) {
            throw new IllegalStateException("Cannot determine target DataSource for lookup key [" + lookupKey + "]");
        }

        logger.debug("Determine data source, [lookup key : " + lookupKey + ", data source key : " + dataSourceKey);

        try {
            return dataSource.getConnection();
        }
        catch (SQLException sqle) {
            logger.error("Get Connection Exception " + dataSource, sqle);

            if (!this.disconnectDataSources.contains(dataSourceKey)) {
                this.disconnectDataSources.add(dataSourceKey);
            }

            if (DBType.MASTER.equals(lookupKey)) {
                this.switchToAvailableDataSource();
            }
            else if (DBType.SLAVE.equals(lookupKey)) {
                this.resolvedSlaveDataSources.remove(dataSourceKey);
            }
            else {
                this.switchToAvailableDataSource();
            }

            throw sqle;
        }
        finally {
            // 清除当前线程数据库类型
            DBContextHolder.clear();

            logger.debug("清除当前线程数据库类型。DBType：{}", DBContextHolder.getCurrentDBType());
        }
    }

    private static final String MASTER_DATASOURCE_KEY = "_master";
    private static final Random RANDOM = new Random();

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        Object lookupKey = this.determineCurrentLookupKey();
        Object dataSourceKey = null;
        DataSource dataSource = null;

        if (DBType.SLAVE.equals(lookupKey)) {
            if (!this.resolvedSlaveDataSources.isEmpty()) {
                int size = this.resolvedSlaveDataSources.size();
                int index = 0;
                int targetIndex = 0;

                if (size > 1) {
                    targetIndex = RANDOM.nextInt(size);
                }

                for (Map.Entry<Object, DataSource> entry : this.resolvedSlaveDataSources.entrySet()) {
                    if (index == targetIndex) {
                        dataSourceKey = entry.getKey();
                        dataSource = entry.getValue();
                        break;
                    }

                    index++;
                }
            }
            else {
                logger.debug("Resolved slave data source is empty.");
            }
        }

        if (dataSource == null) {
            dataSourceKey = MASTER_DATASOURCE_KEY;
            dataSource = this.getCurrentDataSource();
        }

        if (dataSource == null) {
            throw new IllegalStateException("Cannot determine target DataSource for lookup key [" + lookupKey + "]");
        }

        logger.debug("Determine data source, [lookup key : " + lookupKey + ", data source key : " + dataSourceKey);

        try {
            return dataSource.getConnection(username, password);
        }
        catch (SQLException sqle) {
            logger.error("Get Connection Exception " + dataSource, sqle);

            if (!this.disconnectDataSources.contains(dataSourceKey)) {
                this.disconnectDataSources.add(dataSourceKey);
            }

            if (DBType.MASTER.equals(lookupKey)) {
                this.switchToAvailableDataSource();
            }
            else if (DBType.SLAVE.equals(lookupKey)) {
                this.resolvedSlaveDataSources.remove(dataSourceKey);
            }
            else {
                this.switchToAvailableDataSource();
            }

            throw sqle;
        }
        finally {
            // 清除当前线程数据库类型
            DBContextHolder.clear();

            logger.debug("清除当前线程数据库类型。DBType：{}", DBContextHolder.getCurrentDBType());
        }
    }

    /**
     * 检索当前目标数据源。
     * 
     * @return
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    protected DataSource determineTargetDataSource() {
        return null;
    }

    /**
     * 
     * @param dataSource
     * @return
     * @throws IllegalArgumentException
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    protected DataSource resolveSpecifiedDataSource(Object dataSource) throws IllegalArgumentException {
        if (dataSource instanceof DataSource) {
            return (DataSource) dataSource;
        }
        else if (dataSource instanceof String) {
            return this.dataSourceLookup.getDataSource((String) dataSource);
        }
        else {
            throw new IllegalArgumentException("Illegal data source value - only [javax.sql.DataSource] and String supported: " + dataSource);
        }
    }

    /**
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    protected void switchToAvailableDataSource() {
        try {
            if (this.lock.incrementAndGet() > 1) {
                return;
            }

            if (this.currentDataSource == this.resolvedStandbyDataSource) {
                if (this.isDataSourceAvailable(this.resolvedMasterDataSource)) {
                    this.currentDataSource = this.resolvedMasterDataSource;
                }
            }
            else {
                this.currentDataSource = this.resolvedMasterDataSource;
                if (!this.isDataSourceAvailable(this.resolvedMasterDataSource)) {
                    this.currentDataSource = this.resolvedStandbyDataSource;
                }
            }
        }
        finally {
            this.lock.decrementAndGet();
        }
    }

    /**
     * 
     * @param dataSource
     * @return
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    private boolean isDataSourceAvailable(DataSource dataSource) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();

            boolean success = stmt.execute(this.checkAvailableSql);

            stmt.close();

            return success;
        }
        catch (SQLException e) {
            logger.error("CheckDataSourceAvailable Exception", e);
            return false;
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    logger.error("Close Connection Exception", e);
                }
            }
        }
    }

    /**
     * 
     * @return
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    public DataSource getCurrentDataSource() {
        if (this.currentDataSource == null) {
            this.currentDataSource = this.resolveSpecifiedDataSource(this.masterDataSource);
        }

        return this.currentDataSource;
    }

    /**
     * 检测数据源有效性的守护线程类。
     *
     * @author ChenFangjie(2015年2月7日 下午4:37:47)
     * 
     * @since 1.0.0
     * 
     * @version 1.0.0
     *
     */
    private class CheckDataSourceDaemonThread extends Thread {

        public CheckDataSourceDaemonThread() {
            this.setDaemon(true);
            this.setName("CheckDataSourceDaemonThread");
        }

        @Override
        public void run() {
            while (true) {
                if (!disconnectDataSources.isEmpty()) {
                    for (Object name : disconnectDataSources) {
                        if (MASTER_DATASOURCE_KEY.equals(name)) {
                            if (isDataSourceAvailable(resolvedMasterDataSource)) {
                                disconnectDataSources.remove(name);
                                switchToAvailableDataSource();
                            }
                        }
                        else {
                            DataSource dataSource = resolveSpecifiedDataSource(slaveDataSources.get(name));
                            if (isDataSourceAvailable(dataSource)) {
                                disconnectDataSources.remove(name);
                                resolvedSlaveDataSources.put(name, dataSource);
                            }
                        }

                        try {
                            Thread.sleep(checkTimeInterval);
                        }
                        catch (InterruptedException e) {
                            logger.warn("Check Master InterruptedException", e);
                        }
                    }
                }
                else {
                    try {
                        Thread.sleep(checkTimeInterval);
                    }
                    catch (InterruptedException e) {
                        logger.warn("Check Master InterruptedException", e);
                    }
                }
            }
        }
    }

}
