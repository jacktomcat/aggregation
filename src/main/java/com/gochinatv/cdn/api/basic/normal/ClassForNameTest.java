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
	
	public void index(){
		System.out.println("执行index方法");
	}
	
	public static void main(String[] args) {
		
		
		try {
			/**
			 * 这里会执行static静态代码块,不会执行构造方法
			 */
			Class.forName("com.gochinatv.cdn.api.basic.normal.ClassForNameTest");
			
			Class<?> clazz = Class.forName("com.gochinatv.cdn.api.basic.normal.ClassForNameTest");
			try {
				/**
				 * 执行构造方法
				 */
				ClassForNameTest forname = (ClassForNameTest)clazz.newInstance();
				forname.index();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		//String s1="jackt";
		//String s2="jack"+"t";
		//System.out.println(s1==s2);//true
		//System.out.println(s1==s1.intern());//true
		try {
			Connection con = DriverManager.getConnection("","","");
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
}
