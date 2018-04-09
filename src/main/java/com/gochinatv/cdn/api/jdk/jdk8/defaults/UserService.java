package com.gochinatv.cdn.api.jdk.jdk8.defaults;

public interface UserService {

	String getName(String name);
	
	default String getInfo(String name){
		return "hello:"+name;
	}
	
}
