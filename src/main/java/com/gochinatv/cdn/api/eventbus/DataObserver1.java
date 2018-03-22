package com.gochinatv.cdn.api.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * 使用guava的@Subscribe 订阅消息
 * 
 * @author jacktomcat
 *
 */
public class DataObserver1 {

	/**
	 * 只有通过@Subscribe注解的方法才会被注册进EventBus 而且方法有且只能有1个参数
	 * 消费String类型的消息
	 * @param msg
	 */
	@Subscribe
	public void consumer(String msg) {
		System.out.println("String msg: " + msg);
	}

}
