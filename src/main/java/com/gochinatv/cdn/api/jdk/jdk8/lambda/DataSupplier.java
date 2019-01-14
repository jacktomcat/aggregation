package com.gochinatv.cdn.api.jdk.jdk8.lambda;

import java.util.List;

@FunctionalInterface
public interface DataSupplier {

	/**
	 * 自定义
	 * @param key
	 * @return
	 */
	public List<String> getData(String key);

	//public abstract void run(String text);
	
}
