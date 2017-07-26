package com.gochinatv.cdn.api.jdk.jdk8;

import java.time.LocalDate;
import java.time.Period;


/**
 * 时间长度
 *  Period表示以年、月、日衡量的时长。例如，“3年2个月零6天”。
 */
public class PeriodTest {
   
	
	public static void main(String[] args) {
		Period sixMonths = Period.of(2016,7,26);
        int days = sixMonths.getDays();

        LocalDate date = LocalDate.now();
        LocalDate future = date.plus(sixMonths);

        System.out.println("Period ofMonths 6 = " + sixMonths);//P2016Y7M26D
        System.out.println("Period ofMonths getDays = " + days);


        System.out.println("Period LocalDate plus = " + future);

	}
}
