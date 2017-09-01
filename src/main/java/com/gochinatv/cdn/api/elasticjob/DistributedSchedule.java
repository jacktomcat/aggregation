package com.gochinatv.cdn.api.elasticjob;


/**
 * 分布式任务调度
 * @author tingyun
 *
 */
public interface DistributedSchedule {

	/**
	 * 任务调度
	 * @param shardingTotalCount  分片总数
	 * @param shardingItem        执行当前的分片
	 */
	public void schedule(int shardingTotalCount,int shardingItem);
	
}
