package com.gochinatv.cdn.api.jdk.jdk8;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * 
 * https://dotnetcodr.com/2015/01/11/formatting-dates-in-java-8-using-datetimeformatter/
 * 
 * 
 * @author jacktomcat
 *
 */
public class LocalDateTest {
   
	public static void main(String[] args) {
        LocalDate today = LocalDate.now();    //相当于 new Date()
        System.out.println("LocalDate Date= "+today);
        System.out.println("LocalDate Year= "+today.getYear()); //获取年份
        System.out.println("LocalDate Month= "+today.getMonth());//MARCH 返回月份
        System.out.println("LocalDate MonthValue= "+today.getMonthValue());
        System.out.println("LocalDate DayOfYear= "+today.getDayOfYear());
        System.out.println("LocalDate DayOfMonth= "+today.getDayOfMonth());
        System.out.println("LocalDate DayOfWeek= "+today.getDayOfWeek());//MONDAY 返回星期几
        System.out.println("LocalDate adjustInto = "+today.adjustInto(LocalDate.of(1980, 1, 1)));
        System.out.println("LocalDate EpochDay= "+today.toEpochDay());//1970 距离现在的日期天数
        System.out.println("LocalDate lengthOfMonth= "+today.lengthOfMonth());//一个月有多少天
        System.out.println("LocalDate isLeapYear= "+today.isLeapYear());//false 是否是闰年
        System.out.println("LocalDate DateTimeFormatter= "+today.format(DateTimeFormatter.ISO_DATE));//当前时间格式化
        
        System.out.println("LocalDate to LocalDateTime = " + today.atTime(1, 1, 1));//LocalDate转换成LocalDateTime
        
        System.out.println("===================设置日期并格式化=================");
        System.out.println("LocalDate custom parse = " + LocalDate.parse("1980-09-09",DateTimeFormatter.ofPattern("yyyy-MM-dd")));//设置日期
        System.out.println("LocalDate custom of = " + LocalDate.of(1980, 3, 3));//设置日期
        
        System.out.println("===================操纵,解析=================");
        System.out.println("LocalDate withDayOfYear = " + today.withDayOfYear(3));//设置日期,设置为一年中的第三天
        System.out.println("LocalDate withDayOfMonth = " + today.withDayOfMonth(10));//设置当前月的第10天
        System.out.println("LocalDate withYear = " + today.withYear(1980));//设置年份为1980
        System.out.println("LocalDate DAY_OF_WEEK = " + today.with(ChronoField.DAY_OF_WEEK, 3));//返回当前日期周的第三天日期
        System.out.println("LocalDate after 1 week = " + today.plus(1, ChronoUnit.WEEKS));//1周后
        
        System.out.println("LocalDate TemporalAdjusters firstDayOfMonth = " + today.with(TemporalAdjusters.firstDayOfMonth()));//返回本月第一天
        
        LocalDate monday = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate sunday = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        System.out.println("LocalDate MONDAY = " + monday + " LocalDate sunday = "+sunday);//当前周的周一和周日
        
        
      //Temporal是接口  所有的日期时间类 实现了该接口
      //from 依据传入的Temporal对象创建对象实例
      //now 依据系统时钟创建Temporal对象
      //of 有Temporal对象的某个部分创建该对象的实例
      //parse 由字符串创建Temporal对象的实例
      //atOffset 将Temporal对象和某个时区偏移相结合
      //atZone 将Temporal对象和某个时区相结合
      //format 使用某个指定的格式器将Temporal对象转换为字符串(Instant类不提供该方法)
      //get 读取Temporal对象的某一部分
      //minus 创建Temporal对象的一个副本,通过将当前Temporal对象的值减去一定的时长创建该副本
      //plus 创建Temporal对象的一个副本,通过将当前Temporal对象的值加上一定的时长创建该副本
      //with 以该Temporal对象为模板,对某些状态进行修改创建该对象的副本
        
	}
}
