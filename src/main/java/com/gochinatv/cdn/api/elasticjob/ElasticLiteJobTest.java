package com.gochinatv.cdn.api.elasticjob;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.script.ScriptJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

public class ElasticLiteJobTest {
   
	// 定义Zookeeper注册中心配置对象
    private static ZookeeperConfiguration zkConfig = null;
    
 // 定义Zookeeper注册中心
    private static CoordinatorRegistryCenter regCenter = null;
    
    static{
    	zkConfig = 
        		new ZookeeperConfiguration("localhost:2181", "topn-job", 1000, 3000, 3);
    	regCenter = new ZookeeperRegistryCenter(zkConfig);
    	regCenter.init();
    }
    
    private static LiteJobConfiguration createJobConfiguration() {
        // 创建作业配置
         
        JobCoreConfiguration coreConfig = JobCoreConfiguration.newBuilder("myDataFlowTest", "0 0/1 * * * ?", 10).shardingItemParameters("0=0,1=1,2=2,3=3,4=4,5=5,6=6,7=7,8=8,9=9").build();
        DataflowJobConfiguration dataflowJobConfig = new DataflowJobConfiguration(coreConfig, JavaDataflowJob.class.getCanonicalName(), true);
        LiteJobConfiguration result = LiteJobConfiguration.newBuilder(dataflowJobConfig).build();
        return result;
    }
    
    public static void main(String[] args) {
		
    	new JobScheduler(regCenter, createJobConfiguration()).init();
    	
	}
}
