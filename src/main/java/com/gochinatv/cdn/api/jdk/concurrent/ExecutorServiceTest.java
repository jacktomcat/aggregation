package com.gochinatv.cdn.api.jdk.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;


/**
 * 
 * @author jacktomcat
 *
 */
public class ExecutorServiceTest {

	ExecutorService threadPool = Executors.newFixedThreadPool(10);
    AtomicInteger atomic = new AtomicInteger(0);
	int counter = 0;
    
	
	@Test
	public void testExecute(){
		for (int i = 0; i < 100; i++) {
			threadPool.execute(() -> {
				try {
					int incrementAndGet = atomic.incrementAndGet();
					System.out.println("线程安全, 当前任务第 " + incrementAndGet + " 个");
					//counter++;
					//System.out.println("线程非安全, 当前任务第 " + counter + " 个");
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		threadPool.shutdown();
		try {
			threadPool.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testSubmit() throws Exception{
		for (int i = 0; i < 100; i++) {
			Future<?> future = threadPool.submit(new Runnable() {
				@Override
				public void run() {
					int incrementAndGet = atomic.incrementAndGet();
					System.out.println("线程安全, 当前任务第 " + incrementAndGet + " 个");
				}
			});
			Object object = future.get();
			System.out.println(object+"::");//返回null
		}
		threadPool.shutdown();
		try {
			threadPool.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void testSubmit1() throws Exception{
		for (int i = 0; i < 100; i++) {
			String result=""+i;
			Future<?> future = threadPool.submit(new Runnable() {
				@Override
				public void run() {
					int incrementAndGet = atomic.incrementAndGet();
					System.out.println("线程安全, 当前任务第 " + incrementAndGet + " 个");
				}
			},result);//参数两个参数的返回结果,需要自己定义
			Object object = future.get();
			System.out.println(object+"::");//返回i
		}
		threadPool.shutdown();
		try {
			threadPool.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void testSubmitCallable() throws Exception{
		for (int i = 0; i < 100; i++) {
			Future<String> future = threadPool.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					int incrementAndGet = atomic.incrementAndGet();
					return incrementAndGet+"批次";
				}
			});
			
			Object object = future.get();
			System.out.println(object+"::");//返回i
		}
		threadPool.shutdown();
		try {
			threadPool.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
