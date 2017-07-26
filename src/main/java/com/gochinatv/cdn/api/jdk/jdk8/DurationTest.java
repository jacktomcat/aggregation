package com.gochinatv.cdn.api.jdk.jdk8;


import java.time.Duration;
import java.time.LocalDateTime;

/**
 * https://dotnetcodr.com/2015/01/03/java-8-date-and-time-api-the-instant-class/
 * 
 * http://letscoding.iteye.com/blog/2065350
 *
 * 时间长度
 *   Duration表示以秒和纳秒为基准的时长。例如，“23.6秒”。
 * @author jacktomcat
 *
 */
public class DurationTest {
   
	
	public static void main(String[] args) {
		Duration duration = Duration.ofDays(3);  //PT72H

		System.out.println(duration);


		LocalDateTime now = LocalDateTime.now();
		LocalDateTime ofTime = LocalDateTime.of(2017,7,10,12,12,12);

		Duration between = Duration.between(ofTime, now);
		System.out.println("Duration between days = " + between.toDays());
		System.out.println("Duration between days = " + between.toHours());
		
	}
}
