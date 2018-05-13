package com.gochinatv.cdn.api.shardingjdbc;

import org.apache.commons.dbcp.BasicDataSource;
import javax.sql.DataSource;

/**
 * ${DESCRIPTION}
 *
 * @auhtor jacktomcat
 * @create 2018-05-12 下午9:41
 */
public class DataSourceUtil {


    private static final String URL_PREFIX = "jdbc:mysql://localhost:3306/";

    private static final String USER_NAME = "upenv";

    private static final String PASSWORD = "upenv";

    public static DataSource createDataSource(final String dataSourceName) {
        BasicDataSource result = new BasicDataSource();
        result.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        result.setUrl(URL_PREFIX + dataSourceName);
        result.setUsername(USER_NAME);
        result.setPassword(PASSWORD);
        return result;
    }

}
