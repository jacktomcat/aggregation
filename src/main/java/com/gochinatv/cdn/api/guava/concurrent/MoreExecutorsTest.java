package com.gochinatv.cdn.api.guava.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.FutureFallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 *
 * @author jacktomcat
 */
public class MoreExecutorsTest {
	
	public static void main(String[] args) {
		
		// 除了ListenableFuture,guava还提供了FutureCallback接口,相对来说更加方便一些. 
		ListeningExecutorService listeningDecorator = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
		
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
		
		
		// 注意这里没用指定执行回调的线程池,从输出可以看出，默认是和执行异步操作的线程是同一个
		Futures.addCallback(listenableFuture, new FutureCallback<List<String>>() {

			@Override
			public void onSuccess(List<String> result) {
				//result
				System.out.println("成功");
			}

			@Override
			public void onFailure(Throwable t) {
				System.out.println("失败");
			}
		});
		
		
		listeningDecorator.shutdown();
		try {
			listeningDecorator.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
