package com.gochinatv.cdn.api.elasticjob;

import java.util.ArrayList;
import java.util.List;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

public class JavaDataflowJob implements DataflowJob<Foo> {
    private DataProcess dataProcess = DataProcessFactory.getDataProcess();
     
    @Override
    public List<Foo> fetchData(ShardingContext context) {
        List<Foo> result = new ArrayList<Foo>();
        result = dataProcess.getData(context.getShardingParameter(), context.getShardingTotalCount());
        System.out.println("处理:"+context.getShardingParameter()+"分配任务，长度为："+result.size());
        //System.out.println(String.format("------Thread ID: %s, Date: %s, Sharding Context: %s, Action: %s, Data: %s", Thread.currentThread().getId(), new Date(), context, "fetch data",result));
        return result;
    }
    
     
    @Override
    public void processData(ShardingContext shardingContext, List<Foo> data) {
        //System.out.println(String.format("------Thread ID: %s, Date: %s, Sharding Context: %s, Action: %s, Data: %s", Thread.currentThread().getId(), new Date(), shardingContext, "finish data",data));
    	for(Foo foo:data){
    		System.out.println(shardingContext.getShardingItem());
            dataProcess.setData(foo.getId());
        }
    	System.out.println("处理:"+shardingContext.getShardingParameter()+"任务结束，长度为："+data.size());
    }
 
}
