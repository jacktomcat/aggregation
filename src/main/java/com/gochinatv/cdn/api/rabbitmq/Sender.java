package com.gochinatv.cdn.api.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Sender {
    
	public static void sendMessage(){
		Connection conn = Factory.getConnection();
		try {
			Channel channel = conn.createChannel();
			String exchangeName="test-channel";
			channel.exchangeDeclare(exchangeName, "direct", true);
			String queueName = "test-queue";//channel.queueDeclare().getQueue();
			channel.queueDeclare(queueName, true, false, false, null);
			channel.queueBind(queueName, exchangeName, "vrs.task.assign");
			channel.basicPublish(exchangeName, "vrs.task.assign", null, "hello world".getBytes());
			
			
			channel.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		for(int i=0;i<100;i++){
			sendMessage();
		}
	}
}
