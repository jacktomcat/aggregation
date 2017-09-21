package com.gochinatv.cdn.api.basic.normal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClassForNameTest {
  
	static{
		System.out.println("执行static代码块");
	}
	
	public ClassForNameTest(){
		System.out.println("执行构造方法");
	}
	
	public static void main(String[] args) {
		
		
		try {
			/**
			 * 这里会执行static静态代码块,不会执行构造方法
			 */
			Class.forName("com.gochinatv.cdn.api.basic.normal.ClassForNameTest");
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		//String s1="zhuhu";
		//String s2="zhu"+"hu";
		//System.out.println(s1==s2);
		//System.out.println(s1==s1.intern());
		try {
			Connection con = DriverManager.getConnection("","","");
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
}
