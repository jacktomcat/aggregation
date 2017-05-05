package com.gochinatv.cdn.api.zk;

public class TestZK {
    public static int notifition=0;
    public static int slave = 0;
    public static int rpcStatus = 0;
    
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 20; i++) {
			RpcThread thread = new RpcThread();
			thread.start();
			Thread.sleep(1000L);
		}
	}
}

class RpcThread extends Thread{
	
	public RpcThread(){
		
	}
	
	@Override
	public void run() {
		init();
	}
	
	private static void init(){
		CuratorClient curatorClient = new CuratorClient();
		  RpcServerStatus rpcServerStatus = new DefaultRpcServerStatus(curatorClient,(++TestZK.rpcStatus));
	      rpcServerStatus.addDataEventListenter("AlarmSettingsService", new ActionListener() {
				@Override
				public void action() {
					print();
				}
		   });
	      
	       if(rpcServerStatus.isLeader()){
				//rpcServerStatus.triggerDataEvent("AlarmSettingsService");
		   }
	       
	       if(!rpcServerStatus.isLeader()){
	    	   slave();
	       }
	}
	
	public static void print(){
		System.out.println("**共同加载方法**" + (++TestZK.notifition));
	}
	
	public static void slave(){
		System.out.println("**slave**" + (++TestZK.slave));
	}
}
