package com.gochinatv.cdn.api.jdk.concurrent;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * http://blog.csdn.net/u011734144/article/details/52351841
 * @author jacktomcat
 *
 */
public class FutureTest {

	ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
	CompletionService<String> completionService = new ExecutorCompletionService<>(fixedThreadPool);
	
	@Test
	public void testAsync(){
	    
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
	
	
	@Test
	public void testSync(){
		String[] names = new String[100];
		ExecutorService service = Executors.newFixedThreadPool(10);
		List<Future<String>> result = Lists.newArrayList();
		for(int i=0;i<100;i++){
			names[i]="names"+i;
		}
		
	    for(int i=0;i<names.length;i++){
	    	Future<String> submit = service.submit(expectDbAsync(names[i]));
	    	result.add(submit);
	    }
	    
	    getResult(result);
	    
	    service.shutdown();
	    try {
	    	service.awaitTermination(5, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	  public void getResult(List<Future<String>> fList) {
	        ExecutorService service = Executors.newSingleThreadExecutor();
	        service.execute(()->{
	        	fList.forEach(f->{
	        		try {
                        while (true) {
                            if (f.isDone() && !f.isCancelled()) {
                                System.out.println( "Result:" + f.get());
                                break;
                            }else if(f.isCancelled()){
                            	System.out.println("任务取消" + ",Result:" + f.get());
                            } else {
                                Thread.sleep(1000);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
	        	});
	        });
	        service.shutdown();
	        try {
				service.awaitTermination(5, TimeUnit.MINUTES);
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
