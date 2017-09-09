package com.gochinatv.cdn.api.redis;

import java.time.Duration;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * 通过redis-stat-0.4.14.jar监控redis的情况
 * 
 * java -jar redis-stat-0.4.14.jar --server
 * 启动之后访问: http://localhost:63790/#
 * 
 * #设置超过最大内存之后的收回key的策略
 * config set maxmemory-policy allkeys-lru
 * 
 * #设置最大的内存30M,并验证上面的收回策略是否生效
 * congig set maxmemory 30M
 * config rewrite
 * 
 * @author jacktomcat
 *
 */
public class RedisConnection {

	
	public static void main(String[] args) {
		Jedis jedis = getResource();
		for(int i=0;i<300000;i++){
			byte[] key = ("id:"+i).getBytes();
			byte[] value = ("{'name':'zhagnsan','sex':'1','age':'100','uri':'http://www.baidu.com'}:").getBytes();
			jedis.set(key, value);
		}
		jedis.close();
	}
	
	
	public static Jedis getResource(){
		JedisPool pool = buildPool();
		Jedis resource = pool.getResource();
		return resource;
	}
	
	private static JedisPool buildPool() {
		final GenericObjectPoolConfig poolConfig = buildPoolConfig();
		JedisPool jedisPool = new JedisPool(poolConfig, "localhost");
		return jedisPool;
	}
	
	public static GenericObjectPoolConfig buildPoolConfig() {
	    final GenericObjectPoolConfig poolConfig = new JedisPoolConfig();
	    poolConfig.setMaxTotal(128);
	    poolConfig.setMaxIdle(128);
	    poolConfig.setMinIdle(16);
	    poolConfig.setTestOnBorrow(true);
	    poolConfig.setTestOnReturn(true);
	    poolConfig.setTestWhileIdle(true);
	    poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
	    poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
	    poolConfig.setNumTestsPerEvictionRun(3);
	    poolConfig.setBlockWhenExhausted(true);
	    return poolConfig;
	}
	
}
