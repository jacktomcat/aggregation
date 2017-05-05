package com.gochinatv.cdn.api.zk;

import org.apache.curator.framework.recipes.leader.LeaderLatch;

public interface RpcServerStatus {

	public boolean isLeader();
	
	public void addDataEventListenter(String key,ActionListener action);
	
	public void triggerDataEvent(String key);
	
	public LeaderLatch getLeader();
}
