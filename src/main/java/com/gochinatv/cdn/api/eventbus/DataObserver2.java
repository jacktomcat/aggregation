package com.gochinatv.cdn.api.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * 使用guava的@Subscribe 订阅消息
 * 
 * @author jacktomcat
 *
 */
public class DataObserver2 {

	/**
	 * post() 不支持自动装箱功能,只能使用Integer,不能使用int,否则handlersByType的Class会是int而不是Intege
	 * 而传入的int msg参数在post(int msg)的时候会被包装成Integer,导致无法匹配到
	 */
	@Subscribe
	public void consumer(Integer msg) {
		System.out.println("Integer msg: " + msg);
	}

}
