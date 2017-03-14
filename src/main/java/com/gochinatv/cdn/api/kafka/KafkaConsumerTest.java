package com.gochinatv.cdn.api.kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;



public class KafkaConsumerTest {
   
	
	public static void main(String[] args) {
	     Properties props = new Properties();
	     props.put("bootstrap.servers", "192.168.2.150:9092");
	     props.put("group.id", "test");
	     props.put("enable.auto.commit", "true");
	     props.put("auto.commit.interval.ms", "1000");
	     props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
	     consumer.subscribe(Arrays.asList("my-topic"));
	     while (true) {
	         ConsumerRecords<String, String> records = consumer.poll(100);
	         for (ConsumerRecord<String, String> record : records)
	             System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
	        
	         //下面设置后，导致消费端重复消费，如果不指定offset那么消费之后默认移动offset的位置
	         TopicPartition partition = new TopicPartition("my-topic", 0);
	         consumer.seek(partition, 9911063);
	     }
	}
	
}
