package com.gochinatv.cdn.api.jdk.jdk8;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 
 * https://dotnetcodr.com/2015/01/11/formatting-dates-in-java-8-using-datetimeformatter/
 * @author jacktomcat
 *
 */
public class LocalDateTest {
   
	public static void main(String[] args) {
		
		//Current Date
		System.out.println("=====================LocalDate=======================");
        LocalDate today = LocalDate.now();    //相当于 new Date()
        System.out.println("LocalDate Date= "+today);
        System.out.println("LocalDate Year= "+today.getYear());
        System.out.println("LocalDate Month= "+today.getMonth());//MARCH 返回月份
        System.out.println("LocalDate MonthValue= "+today.getMonthValue());
        System.out.println("LocalDate DayOfYear= "+today.getDayOfYear());
        System.out.println("LocalDate DayOfMonth= "+today.getDayOfMonth());
        System.out.println("LocalDate DayOfWeek= "+today.getDayOfWeek());//MONDAY 返回星期几
        System.out.println("LocalDate adjustInto = "+today.adjustInto(LocalDate.of(1980, 1, 1)));
        System.out.println("LocalDate EpochDay= "+today.toEpochDay());//1970 距离现在的日期天数
        System.out.println("LocalDate DateTimeFormatter= "+today.format(DateTimeFormatter.ISO_DATE));//当前时间格式化
        System.out.println("LocalDate custom parse = " + LocalDate.parse("2017-09-09",DateTimeFormatter.ofPattern("yyyy-MM-dd")));//设置日期
        System.out.println("LocalDate custom of = " + LocalDate.of(2018, 3, 3));//设置日期
        
	}
}
