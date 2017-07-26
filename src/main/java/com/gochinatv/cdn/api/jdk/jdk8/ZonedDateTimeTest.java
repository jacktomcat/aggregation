package com.gochinatv.cdn.api.jdk.jdk8;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonedDateTimeTest {
  
	public static void main(String[] args) {
		
		ZonedDateTime Sao_Paulo = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));

		ZonedDateTime defaults = ZonedDateTime.now(ZoneId.systemDefault());

		System.out.println(Sao_Paulo);
		System.out.println(defaults);
	}
}
