package com.gochinatv.cdn.api.zk;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DefaultRpcServerStatus implements RpcServerStatus {
	
	private Logger logger = LoggerFactory.getLogger(DefaultRpcServerStatus.class);
	
	private CuratorClient curatorClient;
	private LeaderLatch leader;
	
	private String alarmZkPrefix="/api";
	
	private final static String ALARM_ZK_LEADER_DIR = "/aggregation/leader";
	private final static String ALARM_ZK_LISTENER_DIR = "/aggregation/listener";
	
	public DefaultRpcServerStatus(CuratorClient curatorClient){
		this.curatorClient = curatorClient;
		init();
	}

	public void init(){
		curatorClient.getClient().start();
		initLeaderLatch();
	}
	
	private void initLeaderLatch(){
		leader = new LeaderLatch(curatorClient.getClient(), ALARM_ZK_LEADER_DIR + this.alarmZkPrefix, "client#");
		try {
			leader.addListener(new LeaderLatchListener() {
                @Override
                public void isLeader() {
                    //System.out.println(leader.getId() +  ":I am leader. I am doing jobs!");
                }

                @Override
                public void notLeader() {
                    //System.out.println(leader.getId() +  ":I am not leader. I will do nothing!");
                }
            });
			leader.start();
		} catch (Exception e) {
			logger.error("leader latch start error:",e);
		}
	}
	
	@Override
	public boolean isLeader() {
		return leader.hasLeadership();
	}

	public void triggerDataEvent(String key){
		try {
			curatorClient.getClient().setData().forPath(getZkPath(key));
		} catch (Exception e) {
			logger.error("change node data error:",e);
		}
	}
	
	public void addDataEventListenter(String key,final ActionListener action){
		
		CuratorFramework client = this.curatorClient.getClient();
		final String path = getZkPath(key);
		
		try {
			ZkUtils.createIfNotExists(client, path);
		} catch (Exception e) {
			logger.error("create node if not exixts error:",e);
		}
		
		final NodeCache cache = new NodeCache(client,path);
		try {
			cache.start();
		} catch (Exception e) {
			logger.error("node cache start error:",e);
		}
		
		cache.getListenable().addListener(new NodeCacheListener() {
			
			public void nodeChanged() throws Exception {
				
				if(!isLeader())
				{
					logger.debug("node data change for path:"+path);
					action.action();
				}
			}
		});
		
	}
	
	private String getZkPath(String key){
		return ALARM_ZK_LISTENER_DIR+alarmZkPrefix+"/"+key;
	}
	
	public void destory(){
		
	}
	
}
