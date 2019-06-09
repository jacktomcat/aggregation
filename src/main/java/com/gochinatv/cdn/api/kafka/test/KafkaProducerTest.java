package com.gochinatv.cdn.api.kafka.test;

import com.alibaba.fastjson.JSONObject;
import com.gochinatv.cdn.api.kafka.Config;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Date;
import java.util.Properties;


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

		 //case 1:
		 //没有任何分区，默认1个分区，发送消息
		 Producer<String, String> producer = new KafkaProducer<>(props);
       for(int i=1;i<100000;i++){
        	 JSONObject object = new JSONObject();
        	 object.put("timestamp", new Date().getTime()/1000);
        	 object.put("dataType", "system");
        	 object.put("actionId", i);
        	 System.out.println(object.toJSONString());
		     producer.send(new ProducerRecord<String, String>("test1-repartition-messages", object.toJSONString()));
			 producer.flush();
			 
			 //Thread.sleep(1000*10L);
			
		 }
     producer.close();
	}

}
