package com.gochinatv.cdn.api.jdk.jdk8;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * 
 * https://dotnetcodr.com/2015/01/11/formatting-dates-in-java-8-using-datetimeformatter/
 * @author jacktomcat
 *
 */
public class LocalTimeTest {
   
	public static void main(String[] args) {
		
        System.out.println("=====================LocalTime=======================");
        LocalTime now = LocalTime.now();

        System.out.println("LocalTime truncatedTo= "+now.truncatedTo(ChronoUnit.MINUTES));//截取到分钟
        System.out.println("LocalTime of = "+LocalTime.of(12, 12));//设置时间
      
	}
}
