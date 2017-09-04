package com.gochinatv.cdn.api.guava.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 *
 * @author jacktomcat
 */
public class MoreExecutorsTest2 {
	
	public static void main(String[] args) {
		ListeningExecutorService listeningDecorator = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
		
		//好的实现应该是提供回调,即异步调用完成后,可以直接回调.本例采用guava提供的异步回调接口,方便很多. 参考   MoreExecutorsTest.java
		ListenableFuture<List<String>> listenableFuture = listeningDecorator.submit(new Callable<List<String>>() {
			@Override
			public List<String> call() throws Exception {
				List<String> data = new ArrayList<String>();
				String logText = "[" + Thread.currentThread().getName()   
                        +"]: guava的Future返回结果"; 
				data.add(logText);
				return data;
			}
		});
		
		
		//注册监听器,即异步调用完成时会在指定的线程池中执行注册的监听器  
		listenableFuture.addListener(new Runnable() {
			@Override
			public void run() {
				try {
					List<String> list = listenableFuture.get();
					//这里是异步获取的结果
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				
			}
		}, Executors.newSingleThreadExecutor());
		
		
		listeningDecorator.shutdown();
		try {
			listeningDecorator.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		/*listeningDecorator.submit(new Runnable() {
			@Override
			public void run() {
				
			}
		}, new ArrayList<String>());*/
		
	}
	
	
}
