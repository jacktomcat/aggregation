package com.gochinatv.cdn.api.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;


/**
 * 
 * 创建消息 topic
 * kafka-topics.bat --create --topic spark-test --zookeeper localhost:2181 --partitions 3 --replication-factor 1
 * 
 * 查看消息topic
 * kafka-topics.bat --describe --zookeeper localhost:2181 --topic spark-test 
 * 
 *
 */
public class KafkaProducerTest {
   
	public static void main(String[] args) throws Exception {
		 Properties props = new Properties();
		 props.put("bootstrap.servers", Config.BROKERS);
		 props.put("acks", "all");
		 props.put("retries", 0);
		 props.put("batch.size", 16384);
		 props.put("linger.ms", 1);
		 props.put("buffer.memory", 33554432);
		 props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		 props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer"); 

		 Producer<String, String> producer = new KafkaProducer<>(props);
		 int i = 0;
		 
		 //case 1:
		 //没有任何分区，默认1个分区，发送消息
        while(i<=10000){
        	 //Thread.sleep(1000L);
		     producer.send(new ProducerRecord<String, String>("q-newlens-appserver-metrics-persist", Integer.toString(i), Integer.toString(i)+"-jackjboss"));
		     System.out.println("================send============="+i);
		     i = i+1;
			producer.flush();
			//producer.close();
		 }
        producer.close();
		 
		 //case 2
		 //发送带时间戳的message
		 /*producer.send(new ProducerRecord<String, String>("spark-test", 0, 1490608032358L, Integer.toString(100), Integer.toString(100)+"-jackjboss"));
		 producer.send(new ProducerRecord<String, String>("spark-test", 1, 1490608031358L, Integer.toString(200), Integer.toString(200)+"-jackjboss"));
		 producer.send(new ProducerRecord<String, String>("spark-test", 2, 1490608039358L, Integer.toString(300), Integer.toString(300)+"-jackjboss"));
		 producer.flush();
		 producer.close();*/
		 
		 //case 3
		 //需要使用命令行创建topic并且指定分区数，同时发送消息至分区
		 /*while(true){
			 Thread.sleep(1000L);
		 producer.send(new ProducerRecord<String, String>("spark-test", 0, Integer.toString(100), Integer.toString(100)+"-jackjboss"));
		 producer.send(new ProducerRecord<String, String>("spark-test", 1, Integer.toString(200), Integer.toString(200)+"-jackjboss"));
		 producer.send(new ProducerRecord<String, String>("spark-test", 2, Integer.toString(300), Integer.toString(300)+"-jackjboss"));
		 i = i+1;
		 }*/
		 //producer.flush();
		 //producer.close();
		 
	}
	
}
