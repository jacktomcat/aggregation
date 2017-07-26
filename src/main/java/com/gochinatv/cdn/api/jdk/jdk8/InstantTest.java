package com.gochinatv.cdn.api.jdk.jdk8;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        System.out.println("Instant toEpochMilli = " + instant.toEpochMilli());//系统当前的时间戳

        //Instant from timestamp
        Instant specificTime = Instant.ofEpochMilli(1501034909184L);
        System.out.println("Specific Time = "+specificTime);
 
        //Duration example
        Duration thirtyDay = Duration.ofDays(3);
        System.out.println(thirtyDay);
        
        LocalDateTime localDate1 = LocalDateTime.of(2017, 7, 1,1,1,1);
        LocalDateTime localDate2 = LocalDateTime.of(2017, 7, 1,2,1,1);
        
        Duration between = Duration.between(localDate1, localDate2);
        long millis = between.toMillis();
        long seconds = between.getSeconds();
        System.out.println("Duration between millis = "+ millis);
        System.out.println("Duration between seconds = "+ seconds);
        
        Instant plus3Day = instant.plus(thirtyDay);
        System.out.println("Instant plus 3 days = "+ plus3Day);//加了三天之后的时间戳
	}
}
