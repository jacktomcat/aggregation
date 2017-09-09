package com.gochinatv.cdn.api.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.*;
import redis.clients.util.Hashing;
import redis.clients.util.MurmurHash;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *  redis 分片
 * sharding redis
 * Created by jacktomcat on 17/9/9.
 */
public class RedisShardedConnection {



    public static void main(String[] args) {
        ShardedJedis shardedJedis = getResource();
        for(int i=0;i<3000;i++){
            String key = ("id:"+i);
            String value = ("{'name':'zhagnsan','sex':'1','age':'100','uri':'http://www.baidu.com'}:");
            shardedJedis.set(key, value);
        }


        for(int i=0;i<3000;i++){
            String key = ("id:"+i);
            String value = shardedJedis.get(key);
            System.out.println("value is :" + value);
        }


        shardedJedis.close();
    }


    public static ShardedJedis getResource(){
        ShardedJedisPool pool = buildPool();
        ShardedJedis resource = pool.getResource();
        return resource;
    }

    private static ShardedJedisPool buildPool() {
        GenericObjectPoolConfig poolConfig = RedisConnection.buildPoolConfig();

        JedisShardInfo info1 = new JedisShardInfo("192.168.2.160",6379,5000);
        JedisShardInfo info2 = new JedisShardInfo("192.168.2.150",6379,5000);

        List<JedisShardInfo> shardInfoList = new ArrayList<>(2);
        shardInfoList.add(info1);
        shardInfoList.add(info2);

        //默认采用hashing的方式进行分片
        ShardedJedisPool jedisPool = new ShardedJedisPool(poolConfig, shardInfoList);

        //ShardedJedisPool(GenericObjectPoolConfig poolConfig, List<JedisShardInfo> shards, Hashing algo);


        //ShardedJedisPool(GenericObjectPoolConfig poolConfig, List<JedisShardInfo> shards, Pattern keyTagPattern);

        return jedisPool;
    }


}
