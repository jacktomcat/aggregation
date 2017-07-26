package com.gochinatv.cdn.api.jdk.jdk8;

import java.time.ZoneId;
import java.time.ZoneOffset;


/**
 * ZoneId的子类，ZoneOffset，代表了这种从伦敦格林威治零度子午线开始的时间偏移
 */
public class ZoneIdTest {
   
	
	public static void main(String[] args) {
		System.out.println("=====================ZoneId=======================");
        ZoneId systemDefault = ZoneId.systemDefault();
        System.out.println("ZoneId Default= "+ systemDefault);//获取系统默认时区
        
        ZoneId of = ZoneId.of("Asia/Shanghai");
        
        System.out.println("ZoneID of = " + of);


        ZoneOffset offset  = ZoneOffset.of("Asia/Shanghai");
        System.out.println(offset);
    }
}
