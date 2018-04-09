package com.gochinatv.cdn.api.kafka;

import java.util.Date;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import com.alibaba.fastjson.JSONObject;


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
public class KafkaProducerTestTemp {
   
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
		 //case 1:
		 //2018-03-27 18:52:02
		 //没有任何分区，默认1个分区，发送消息
		 for(int i=1;i<=2;i++){
			 JSONObject object = new JSONObject();
	    	 object.put("timestamp", new Date().getTime()/1000);
	    	 object.put("data_type", "APP");
	    	 object.put("biz_system_id", 1);
	    	 object.put("application_id", 1);
	    	 object.put("instance_id", i);
	    	 object.put("action_type", "");
	    	 object.put("action_id", 8);
	    	 object.put("count", 80*i);
	    	 object.put("resp_time", 750*i);
	    	 object.put("cpu_usage", 20);
	    	 object.put("memory_usage", 24);
	    	 object.put("success_count", 100*2);
		     producer.send(new ProducerRecord<String, String>("ty-svr-action-druid-messages", object.toJSONString()));
			 producer.flush();
		 }
         producer.close();
         Thread.sleep(60*1000);
         
         producer = new KafkaProducer<>(props);
       //没有任何分区，默认1个分区，发送消息
		 for(int i=1;i<=2;i++){
			 JSONObject object = new JSONObject();
	    	 object.put("timestamp", new Date().getTime()/1000);
	    	 object.put("data_type", "APP");
	    	 object.put("biz_system_id", 1);
	    	 object.put("application_id", 1);
	    	 object.put("instance_id", i);
	    	 object.put("action_type", "");
	    	 object.put("action_id", 8);
	    	 object.put("count", 85*i);
	    	 object.put("resp_time", 755*i);
	    	 object.put("cpu_usage", 20);
	    	 object.put("memory_usage", 24);
	    	 object.put("success_count", 105*2);
		     producer.send(new ProducerRecord<String, String>("ty-svr-action-druid-messages", object.toJSONString()));
			 producer.flush();
		 }
         producer.close();
         
         
         Thread.sleep(60*1000);
         producer = new KafkaProducer<>(props);
       //没有任何分区，默认1个分区，发送消息
		 for(int i=1;i<=2;i++){
			 JSONObject object = new JSONObject();
	    	 object.put("timestamp", new Date().getTime()/1000);
	    	 object.put("data_type", "APP");
	    	 object.put("biz_system_id", 1);
	    	 object.put("application_id", 1);
	    	 object.put("instance_id", i);
	    	 object.put("action_type", "");
	    	 object.put("action_id", 8);
	    	 object.put("count", 85*i);
	    	 object.put("resp_time", 755*i);
	    	 object.put("cpu_usage", 20);
	    	 object.put("memory_usage", 24);
	    	 object.put("success_count", 105*2);
		     producer.send(new ProducerRecord<String, String>("ty-svr-action-druid-messages", object.toJSONString()));
			 producer.flush();
		 }
         producer.close();
         
         
         Thread.sleep(60*1000);
         producer = new KafkaProducer<>(props);
       //没有任何分区，默认1个分区，发送消息
		 for(int i=4;i<=5;i++){
			 JSONObject object = new JSONObject();
	    	 object.put("timestamp", new Date().getTime()/1000);
	    	 object.put("data_type", "APP");
	    	 object.put("biz_system_id", 2);
	    	 object.put("application_id", 3);
	    	 object.put("instance_id", i);
	    	 object.put("action_type", "");
	    	 object.put("action_id", 8);
	    	 object.put("count", 85*i);
	    	 object.put("resp_time", 755*i);
	    	 object.put("cpu_usage", 20);
	    	 object.put("memory_usage", 24);
	    	 object.put("success_count", 105*2);
		     producer.send(new ProducerRecord<String, String>("ty-svr-action-druid-messages", object.toJSONString()));
			 producer.flush();
		 }
         producer.close();
         
	
		 
	}

}
