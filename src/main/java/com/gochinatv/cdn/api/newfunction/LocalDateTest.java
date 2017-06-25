package com.gochinatv.cdn.api.newfunction;

import java.time.LocalDate;

public class LocalDateTest {
   
	public static void main(String[] args) {
		//Current Date
        LocalDate today = LocalDate.now();
        System.out.println("Current Date="+today);
        
        LocalDate of = LocalDate.of(2017, 6, 12);
        
		
	}
}
