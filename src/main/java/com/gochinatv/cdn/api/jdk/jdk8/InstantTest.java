package com.gochinatv.cdn.api.jdk.jdk8;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

/**
 * 
 * https://dotnetcodr.com/2015/01/11/formatting-dates-in-java-8-using-datetimeformatter/
 * @author jacktomcat
 * 
 * Instant类是用在机器可读的时间格式上的，它以Unix时间戳的形式存储日期时间
 *
 */
public class InstantTest {
   
	public static void main(String[] args) {
        
		System.out.println("=====================Instant=======================");
        Instant instant = Instant.now();
        instant.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println("Instant now = " + instant);
        System.out.println("Instant EPOCH = " + Instant.EPOCH);
        System.out.println("Instant toEpochMilli = " + instant.toEpochMilli());

        //Instant from timestamp
        Instant specificTime = Instant.ofEpochMilli(1500978707350L);
        System.out.println("Specific Time = "+specificTime);
 
        //Duration example
        //Duration thirtyDay = Duration.ofDays(30);
        //System.out.println(thirtyDay);
	}
}
