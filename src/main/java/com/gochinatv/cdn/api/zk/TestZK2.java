package com.gochinatv.cdn.api.zk;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class TestZK2 {
    
	public static void main(String[] args) throws Exception {
		Timer timer2 = new Timer();
		timer2.schedule(new RpcThread2("node2"), 2000, 1000);
	}
}

class RpcThread2 extends TimerTask{
	
	CuratorClient curatorClient = null;
	RpcServerStatus rpcServerStatus = null;
	String threadName = null;
	public RpcThread2(String threadName){
		curatorClient = new CuratorClient();
	    rpcServerStatus = new DefaultRpcServerStatus(curatorClient);
	    
	    rpcServerStatus.addDataEventListenter("AlarmSettingsService", new ActionListener() {
			@Override
			public void action() {
				load();
			}
	   });
	   this.threadName = threadName;
	}
	
	@Override
	public void run() {
		if(rpcServerStatus.isLeader()){
			load();
			rpcServerStatus.triggerDataEvent("AlarmSettingsService");
	   }
	}
	
	public void load(){
		if(!rpcServerStatus.isLeader()){
			System.out.println(threadName + "**slave 从缓存中加载数据**");
			return;
		}
		System.out.println(threadName + "**从数据库中加载数据**");
	}

}
