package com.gochinatv.cdn.api.third.hystrix;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.Test;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

/**
 * 同步方法调用
 * @author jacktomcat
 *
 */
public class SyncTest extends HystrixCommand<String>{

	private final String name;

    public SyncTest(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("SyncTestCommand"));
        this.name = name;
    }

    @Override
    protected String run() {
    	 return "Hello " + name +" thread:" + Thread.currentThread().getName();
    }
    
    
	public static class UnitTest {

		@Test
		public void syncInvokeTest() {
			// 每个Command对象只能调用一次,不可以重复调用,
			// 重复调用对应异常信息:This instance can only be executed once. Please
			// instantiate a new instance.
			SyncTest syncRequest = new SyncTest("Synchronous-hystrix");
			// 使用execute()同步调用代码,效果等同于:helloWorldCommand.queue().get();
			String result = syncRequest.execute();
			System.out.println("result=" + result);
		}
		
		/**
		 * 异步调用
		 */
		@Test
		public void asyncInvokeTest() {
			SyncTest asyncRequest= new SyncTest("Asynchronous-hystrix");  
	        //异步调用,可自由控制获取结果时机,  
	        Future<String> future = asyncRequest.queue();  
	        //get操作不能超过command定义的超时时间,默认:1秒  
	        String result ="";
			try {
				result = future.get(100, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				e.printStackTrace();
			}  
	        System.out.println("result=" + result);  
	        System.out.println("mainThread=" + Thread.currentThread().getName());
		}
		
		
		
		/**
		 * 带监听的调用
		 */
		@Test
		public void listenerInvokeTest() {
			   // 注册观察者事件拦截
				Observable<String> fs = new SyncTest("Listener").observe();
			
				// 注册完整执行生命周期事件
				fs.subscribe(new Observer<String>() {
					@Override
					public void onCompleted() {
						// onNext/onError完成之后最后回调
						System.out.println("execute onCompleted");
					}

					@Override
					public void onError(Throwable e) {
						// 当产生异常时回调
						System.out.println("onError " + e.getMessage());
						e.printStackTrace();
					}

					@Override
					public void onNext(String v) {
						// 获取结果后回调
						System.out.println("onNext: " + v);
					}
				});
				
				fs.subscribe(new Action1<String>() {
		            @Override
		            public void call(String v) {
		                System.out.println("result: " + v);
		            }

		        });
				
				fs.doAfterTerminate(()-> {
					System.out.println("result:::::::");
				});
				
				String result = fs.toBlocking().single();
				System.out.println("result:" + result);
		}
	}
    
}
