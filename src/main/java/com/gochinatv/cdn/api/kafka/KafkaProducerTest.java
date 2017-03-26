package com.gochinatv.cdn.api.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;


public class KafkaProducerTest {
   
	public static void main(String[] args) {
		 Properties props = new Properties();
		 props.put("bootstrap.servers", "192.168.2.150:9092");
		 props.put("acks", "all");
		 props.put("retries", 0);
		 props.put("batch.size", 16384);
		 props.put("linger.ms", 1);
		 props.put("buffer.memory", 33554432);
		 props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		 props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer"); 

		 Producer<String, String> producer = new KafkaProducer<>(props);
		 
		 int i = 0;
		 while(i<=1000){
			 System.out.println("==============");
		     producer.send(new ProducerRecord<String, String>("spark-test", Integer.toString(i), Integer.toString(i)+"-jackjboss"));
		     i = i+1;
		 }
		 //producer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(100), Integer.toString(100)+"-jackjboss"));
		 //producer.close();
	}
	
}
