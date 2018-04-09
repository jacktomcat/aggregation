package com.gochinatv.cdn.api.jdk.jdk8.enums;

public class TestMain {

	public static void main(String[] args) {
		String name = EnumMethod.BLUE.getName();
		System.out.println(name);
		name = EnumMethod.BLUE.getName("带有前缀的");
		System.out.println(name);
	}
	
}
