package com.gochinatv.cdn.api.third.hystrix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy;



public class CommandUsingSemaphoreIsolation extends HystrixCommand<String>{

	static AtomicInteger counter = new AtomicInteger(0);
    private final int id;

    public CommandUsingSemaphoreIsolation(int id) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                // since we're doing work in the run() method that doesn't involve network traffic
                // and executes very fast with low risk we choose SEMAPHORE isolation
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                		.withExecutionTimeoutInMilliseconds(5000)//超时时间
                		.withExecutionIsolationStrategy(ExecutionIsolationStrategy.SEMAPHORE)//在同一个线程中执行
                		.withCircuitBreakerEnabled(true)
                		.withCircuitBreakerRequestVolumeThreshold(10)
                		.withMetricsRollingPercentileWindowBuckets(1)
                		.withMetricsRollingPercentileBucketSize(10)
                		//.withExecutionIsolationSemaphoreMaxConcurrentRequests(100)
                        //.withExecutionIsolationStrategy(ExecutionIsolationStrategy.THREAD)//在不同的线程中执行
              )
                
        	);
        this.id = id;
    }

    @Override
    protected String run() {
        // a real implementation would retrieve data from in memory data structure
        // or some other similar non-network involved work
        return "ValueFromHashMap_" + id + ","+ Thread.currentThread().getName();
    }
    
	@Override
	protected String getFallback() {
		counter.incrementAndGet();
		return "invoke error";
	}
	
	@Override
	public boolean isCircuitBreakerOpen() {
		return super.isCircuitBreakerOpen();
	}

	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		for(int i=0;i<50000;i++){
			threadPool.submit(()->{
				CommandUsingSemaphoreIsolation semaphore = new CommandUsingSemaphoreIsolation(100);
				String execute = semaphore.execute();
				System.out.println(execute + ","+semaphore.isCircuitBreakerOpen());
			});
		}
		threadPool.shutdown();
		try {
			threadPool.awaitTermination(10, TimeUnit.SECONDS);
			System.out.println("错误调用次数::::::" + counter.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
    
}
