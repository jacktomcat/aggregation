package com.gochinatv.cdn.api.elasticjob;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * @author jacktomcat
 *
 */
public class DistributedMinuteSchedule implements SimpleJob, DistributedSchedule {

	private static final Logger logger = LoggerFactory.getLogger(DistributedMinuteSchedule.class);
	
	@Autowired
	private JobService jobService;
	

	@Override
	public void execute(ShardingContext context) {
		int shardingTotalCount = context.getShardingTotalCount();
		int shardingItem = context.getShardingItem();
		String jobName = context.getJobName();
		schedule(shardingTotalCount, shardingItem);
	}

	
	@Override
	public void schedule(int shardingTotalCount, int shardingItem) {
		List<String> jobs = jobService.getJobs();
		
		jobs.stream().filter(item->{
			return item.hashCode()%shardingTotalCount==shardingItem;
		}).forEach(item->{
			//TODO 这里需要对分片做业务处理
			System.out.println(item);
		});
	}

}

/*
 * import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
 * import
 * com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
 * public class DistributedMinuteSchedule extends AbstractSimpleElasticJob{
 * 
 * @Override public void process(JobExecutionMultipleShardingContext arg0) {
 * List<Integer> shardingItems = arg0.getShardingItems();
 * System.out.println(shardingItems.size()); } }
 */