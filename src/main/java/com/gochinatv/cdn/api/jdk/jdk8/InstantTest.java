package com.gochinatv.cdn.api.jdk.jdk8;

import java.time.Instant;

/**
 * 
 * https://dotnetcodr.com/2015/01/11/formatting-dates-in-java-8-using-datetimeformatter/
 * @author jacktomcat
 *
 */
public class InstantTest {
   
	public static void main(String[] args) {
        System.out.println("=====================Instant=======================");
        Instant instant = Instant.now();
        System.out.println("Instant now = " + instant);
        System.out.println("Instant EPOCH = " + Instant.EPOCH);
	}
}
