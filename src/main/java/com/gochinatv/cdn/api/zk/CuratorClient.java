package com.gochinatv.cdn.api.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorClient {

	private String servers="192.168.2.100:2181";
	private int sessionTimeout = 1000;
	private int connectionTimeout = 1000;
	
	private int baseSleepTimeMs = 20000;
	private int maxRetries = 3;
	private int maxSleepMs = 100000;
	
	private CuratorFramework client;
	
	public CuratorClient(){
		client = CuratorFrameworkFactory.builder()
				.connectString(servers)
				.sessionTimeoutMs(sessionTimeout)
				.connectionTimeoutMs(connectionTimeout)
				.retryPolicy(new ExponentialBackoffRetry(baseSleepTimeMs, maxRetries, maxSleepMs))
				.build();
	}
	
	public CuratorFramework getClient(){
		return client;
	}

	public String getServers() {
		return servers;
	}

	public void setServers(String servers) {
		this.servers = servers;
	}
	
}
