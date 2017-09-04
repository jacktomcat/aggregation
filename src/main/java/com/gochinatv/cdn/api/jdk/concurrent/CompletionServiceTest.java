package com.gochinatv.cdn.api.jdk.concurrent;

import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



public class CompletionServiceTest {

	
	static ExecutorService threadPool = Executors.newFixedThreadPool(10);
	static CompletionService<List<String>> completionService = new ExecutorCompletionService<>(threadPool);
	
	
	public static void main(String[] args) {
		
		//completionService.submit(task)//数据库异步提交
		//completionService.take().get();
		
		threadPool.shutdown();
		try {
			threadPool.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public void expectDbCallBack(List<String> perfDataList){
		perfDataList.forEach(data -> {
			try {
				List<String> list = completionService.take().get();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		});
	}
	
}
