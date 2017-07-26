package com.gochinatv.cdn.api.jdk.jdk8;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 *
 * 时间点
 *
 * https://dotnetcodr.com/2015/01/03/java-8-date-and-time-api-the-instant-class/
 * @author jacktomcat
 * 
 * Instant类是用在机器可读的时间格式上的，它以Unix时间戳的形式存储日期时间
 *
 * 在处理时间和日期的时候，我们通常会想到年，月，日，时，分，秒。然而，这只是时间的一个模型，是面向人类的。
 * 第二种通用模型是面向机器的，或者说是连续的。在此模型中，时间线中的一个点表示为一个很大的数。
 * 这有利于计算机处理。在UNIX中，这个数从1970年开始，以秒为的单位；
 * 同样的，在Java中，也是从1970年开始，但以毫秒为单位。
 *
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
