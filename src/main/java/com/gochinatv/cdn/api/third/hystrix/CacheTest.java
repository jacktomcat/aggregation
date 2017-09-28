package com.gochinatv.cdn.api.third.hystrix;


import org.junit.Test;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * 同步方法调用
 * @author jacktomcat
 *
 */
public class CacheTest extends HystrixCommand<String>{

	private final String name;

    public CacheTest(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("CacheTestCommand"));
        this.name = name;
    }

    @Override
    protected String run() {
    	 return "Hello " + name +" thread:" + Thread.currentThread().getName();
    }
    
    /**
     * 使用缓存这个方法必须要重写
     */
	@Override
	protected String getCacheKey() {
		return "Hello" + name;
	}


	public static class UnitTest {

		/**
		 * 调用带缓存的
		 */
		@Test
		public void cacheInvokeTest(){
			HystrixRequestContext context = HystrixRequestContext.initializeContext();
			
			CacheTest command =new CacheTest("world");
			command.execute();
			// this is the first time we've executed this command with the value of "world" so it should not be from cache
			boolean responseFromCache = command.isResponseFromCache();
			System.out.println("是否从缓存中获取:"+responseFromCache);
			
			CacheTest command1 =new CacheTest("world");
			command1.execute();
			// this is the second time we've executed this command with the same value so it should return from cache
			boolean responseFromCache1 = command1.isResponseFromCache();
			System.out.println("是否从缓存中获取:"+responseFromCache1);
			
			context.shutdown();
			
			context = HystrixRequestContext.initializeContext();
			CacheTest command2 =new CacheTest("world");
			command2.execute();
			// this is a new request context so this should not come from cache
			boolean responseFromCache2 = command2.isResponseFromCache();
			System.out.println("是否从缓存中获取:"+responseFromCache2);
		}
		
	}
    
}
