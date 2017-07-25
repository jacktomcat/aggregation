package com.gochinatv.cdn.api.jdk.jdk8;

import java.time.ZoneId;

public class ZoneIdTest {
   
	
	public static void main(String[] args) {
		System.out.println("=====================ZoneId=======================");
        ZoneId systemDefault = ZoneId.systemDefault();
        System.out.println("ZoneId Default= "+ systemDefault);//获取系统默认时区
	}
}
