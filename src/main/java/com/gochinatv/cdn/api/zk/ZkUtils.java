package com.gochinatv.cdn.api.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class ZkUtils {

	public static boolean createIfNotExists(CuratorFramework client,String path) throws Exception{
		Stat stat = client.checkExists().forPath(path);
		boolean isExistsNode = stat != null;
		if(!isExistsNode){
			client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path);
		}
		return false;
	}
}
