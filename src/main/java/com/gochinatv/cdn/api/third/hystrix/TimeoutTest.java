package com.gochinatv.cdn.api.third.hystrix;

import org.junit.Test;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * 同步方法调用
 * @author jacktomcat
 *
 */
public class TimeoutTest extends HystrixCommand<String>{

	private final String name;

    public TimeoutTest(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("TimeoutTestGroup"))
        		/* 配置依赖超时时间,500毫秒*/  
              .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(500))
              
        	);
        this.name = name;
    }

    @Override
    protected String run() {
    	 //sleep 1 秒,调用会超时  
        int i=0;
    	while(i<=10000000){
        	
        }
    	return "Hello " + name +" thread:" + Thread.currentThread().getName();
    }
    
    @Override  
    protected String getFallback() {  
        return "exeucute Falled";  
    } 
    
    
	public static class UnitTest {

		@Test
		public void timeoutInvokeTest() {
			//每个Command对象只能调用一次,不可以重复调用,  
	        //重复调用对应异常信息:This instance can only be executed once. Please instantiate a new instance.  
			TimeoutTest syncRequest = new TimeoutTest("word");  
	        //使用execute()同步调用代码,效果等同于:helloWorldCommand.queue().get();   
	        String result = syncRequest.execute();  
	        System.out.println("result=" + result);  
		}
	}
    
}
