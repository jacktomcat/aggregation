package com.gochinatv.cdn.api.zk;

public interface RpcServerStatus {

	public boolean isLeader();
	
	public void addDataEventListenter(String key,ActionListener action);
	
	public void triggerDataEvent(String key);
	
}
