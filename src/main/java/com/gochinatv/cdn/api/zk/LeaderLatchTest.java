package com.gochinatv.cdn.api.zk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

public class LeaderLatchTest {
	private static final String PATH = "/demo/leader";

    public static void main(String[] args) {

        List<LeaderLatch> latchList = new ArrayList<>();
        List<CuratorFramework> clients = new ArrayList<>();
        try {
            for (int i = 0; i < 10; i++) {
                CuratorFramework client = getClient();
                clients.add(client);

                final LeaderLatch leaderLatch = new LeaderLatch(client, PATH, "client#" + i);
                leaderLatch.addListener(new LeaderLatchListener() {
                    @Override
                    public void isLeader() {
                        System.out.println(leaderLatch.getId() +  ":I am leader. I am doing jobs!");
                    }

                    @Override
                    public void notLeader() {
                        System.out.println(leaderLatch.getId() +  ":I am not leader. I will do nothing!");
                    }
                });
                latchList.add(leaderLatch);
                leaderLatch.start();
            }
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            for(CuratorFramework client : clients){
//            	if(client.getState()==CuratorFrameworkState.STARTED)
//            		client.close();
//                //CloseableUtils.closeQuietly(client);
//            }

//            for(LeaderLatch leaderLatch : latchList){
//            	if(leaderLatch.getState()==LeaderLatch.State.STARTED)
//					try {
//						leaderLatch.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//                //CloseableUtils.closeQuietly(leaderLatch);
//            }
        }
    }

    private static CuratorFramework getClient() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(6000)
                .connectionTimeoutMs(3000)
                .namespace("demo")
                .build();
        client.start();
        return client;
    }
}