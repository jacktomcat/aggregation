package com.gochinatv.cdn.api.shardingjdbc;

import io.shardingjdbc.core.api.MasterSlaveDataSourceFactory;
import io.shardingjdbc.core.api.config.MasterSlaveRuleConfiguration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ${DESCRIPTION}
 *
 * @auhtor jacktomcat
 * @create 2018-05-12 下午9:39
 */
public class RawJdbcJavaMasterSlaveOnlyMain {


    // CHECKSTYLE:OFF
    public static void main(final String[] args) throws SQLException {
        // CHECKSTYLE:ON
        new RawJdbcRepository(getMasterSlaveDataSource()).demo();
    }

    private static DataSource getMasterSlaveDataSource() throws SQLException {
        MasterSlaveRuleConfiguration masterSlaveRuleConfig = new MasterSlaveRuleConfiguration();
        masterSlaveRuleConfig.setName("demo_ds_master_slave");
        masterSlaveRuleConfig.setMasterDataSourceName("demo_ds_master");
        masterSlaveRuleConfig.setSlaveDataSourceNames(Arrays.asList("demo_ds_slave_0", "demo_ds_slave_1"));
        return MasterSlaveDataSourceFactory.createDataSource(createDataSourceMap(), masterSlaveRuleConfig, new ConcurrentHashMap<String, Object>());
    }

    private static Map<String, DataSource> createDataSourceMap() {
        final Map<String, DataSource> result = new HashMap<>(3, 1);
        result.put("demo_ds_master", DataSourceUtil.createDataSource("demo_ds_master"));
        result.put("demo_ds_slave_0", DataSourceUtil.createDataSource("demo_ds_slave_0"));
        result.put("demo_ds_slave_1", DataSourceUtil.createDataSource("demo_ds_slave_1"));
        return result;
    }


}
