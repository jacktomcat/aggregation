package com.gochinatv.cdn.api.jdk.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

/**
 * http://blog.csdn.net/u011734144/article/details/52351841
 * @author jacktomcat
 *
 */
public class FutureTest {

	
	ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
	CompletionService<String> completionService = new ExecutorCompletionService<>(fixedThreadPool);
	
	@Test
	public void test(){
	    
		String[] names = new String[100];
		for(int i=0;i<100;i++){
			names[i]="names"+i;
		}
	    for(int i=0;i<names.length;i++){
	    	completionService.submit(expectDbAsync(names[i]));
	    }
		
	    for(int i=0;i<names.length;i++){
	    	try {
				String string = completionService.take().get();//无序,先计算完先获取数据
				System.out.println(string);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
	    }
	    
	    
	    
	    fixedThreadPool.shutdown();
	    try {
			fixedThreadPool.awaitTermination(5, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public Callable<String> expectDbAsync(String name){
		Callable<String> task = new Callable<String>() {

			@Override
			public String call() throws Exception {
				int random = RandomUtils.nextInt(1, 300);
				Thread.sleep(random);
				return name;
			}
		};
		return task;
	}
	
}
