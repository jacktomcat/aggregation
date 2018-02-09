package com.gochinatv.cdn.api.eventbus;


/**
 * 
 * @author jacktomcat
 *
 */
public class EventBusBootStrap {

	
	public static void main(String[] args) {
		
		DataObserver1 observer1 = new DataObserver1();  
        DataObserver2 observer2 = new DataObserver2();  
        
        EventBusCenter.register(observer1);  
        EventBusCenter.register(observer2);  
        
        System.out.println("============   start  ====================");  
		
        // 只有注册的参数类型为String的方法会被调用  
        EventBusCenter.post("post string method");  
        EventBusCenter.post(123);  
  
        System.out.println("============ after unregister ============");  
        // 注销observer2  
        EventBusCenter.unregister(observer2);  
        EventBusCenter.post("post string method");  
        EventBusCenter.post(123);  
  
        System.out.println("============    end           =============");  
        
        
        /**
         * 
         * 
         * ============   start  ====================
		 *   String msg: post string method
		 *   Integer msg: 123
		 *============ after unregister ============
		 *   String msg: post string method
		 *============    end           =============
         * 
         * 
         */
        
	}
	
	
}
