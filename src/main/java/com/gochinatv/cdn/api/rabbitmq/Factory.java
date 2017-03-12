package com.gochinatv.cdn.api.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Factory {
   
static ConnectionFactory factory = null;
	
	public static ConnectionFactory getConnectionFactory(){
		if(factory==null){
			factory = new ConnectionFactory();
//			factory.setUsername("zhuhh");
//			factory.setPassword("zhuhh");
//			factory.setVirtualHost("/");
//			factory.setHost("192.168.2.150");
			
			factory.setUsername("gochinatv");
			factory.setPassword("vegovrs");
			factory.setVirtualHost("/");
			factory.setHost("123.57.8.102");
			
			factory.setPort(5672);
		}
		return factory;
	}
	
	
	public static Connection getConnection(){
		ConnectionFactory factory = getConnectionFactory();
		Connection conn = null;
		try {
			conn = factory.newConnection();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String[] args) {
		getConnection();
	}
	
}
