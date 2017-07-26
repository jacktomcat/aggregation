package com.gochinatv.cdn.api.jdk.jdk8;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * 
 * https://dotnetcodr.com/2015/01/11/formatting-dates-in-java-8-using-datetimeformatter/
 * @author jacktomcat
 *
 */
public class LocalDateTimeTest {
   
	public static void main(String[] args) {
		
		
        System.out.println("=====================LocalDateTime=======================");
        LocalDateTime ldf = LocalDateTime.now();
        
        //从LocalDateTime装换为LocalDate和LocalTime
        System.out.println("LocalTime to LocalDate = "+ldf.toLocalDate());//设置时间
        System.out.println("LocalTime to LocalTime = "+ldf.toLocalTime());//设置时间
        System.out.println("LocalDateTime truncatedTo= "+ ldf.atZone(ZoneId.systemDefault()).truncatedTo(ChronoUnit.SECONDS)
        		.getLong(ChronoField.INSTANT_SECONDS));//默认时区,从1970-01-01 到现在的秒数
        
       // ldf.toEpochSecond(ZoneOffset.)
    
	}
}
