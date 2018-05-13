package com.gochinatv.cdn.api.shardingjdbc;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

/**
 * ${DESCRIPTION}
 *
 * https://github.com/sharding-sphere/sharding-sphere-example/blob/2.0.3/sharding-jdbc-raw-jdbc-example/sharding-jdbc-raw-jdbc-java-example/src/main/java/io/shardingjdbc/example/jdbc/java/algorithm/ModuloShardingTableAlgorithm.java
 * @auhtor jacktomcat
 * @create 2018-05-13 上午10:12
 */
public class ModuloShardingTableAlgorithm implements PreciseShardingAlgorithm<Long> {

    @Override
    public String doSharding(final Collection<String> tableNames, final PreciseShardingValue<Long> shardingValue) {
        for (String each : tableNames) {
            if (each.endsWith(shardingValue.getValue() % 2 + "")) {
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }

}
