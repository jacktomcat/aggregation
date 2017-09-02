package com.gochinatv.cdn.api.elasticjob;

import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

@Component
public class JobService {

	/**
	 * mock job
	 * @return
	 */
	public List<String> getJobs(){
		List<String> jobs = Lists.newArrayList();
		for(int i=0;i<1000;i++){
			jobs.add(String.valueOf(i));
		}
		return jobs;
	}
}
