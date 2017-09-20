package com.gochinatv.cdn.api.basic.normal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClassForNameTest {
  
	
	public static void main(String[] args) {
		//Class.forName(className)
		
		//com.mysql.jdbc.Driver
		try {
			Connection con = DriverManager.getConnection("","","");
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
}
