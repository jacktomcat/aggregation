package com.gochinatv.cdn.api.kafka;

import java.text.SimpleDateFormat;
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
public class KafkaProducerComponent {
   
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
	     for(int i=1;i<20;i++){
    	   	 //TOPN
        	 /*JSONObject object = new JSONObject();
        	 object.put("timestamp", new Date().getTime()/1000);
        	 object.put("data_type", "COMP");//COMP|OP。COMP-组件分解 OP-组件操作分析
        	 object.put("biz_system_id", 1);
        	 object.put("application_id", i%3==0?1:i%3);
        	 object.put("instance_id", i%5==0?1:i%5);
        	 object.put("action_type", "TX");//Action类型：TX|IF|BG
        	 object.put("action_id", i);
        	 object.put("tx_biz_system_id", 1);
        	 object.put("tx_application_id", i%3==0?1:i%3);
        	 object.put("tx_action_id", i%5==0?1:i%5);
        	 object.put("component_type", "External");
        	 object.put("component_subtype", "Dubbo");//Dubbo|Thrift|WebService
        	 object.put("component_name", "/userService/getUserName"+(i));
        	 object.put("call_mode", 1);
        	 object.put("op_name", "127.0.0.1");
        	 object.put("resp_time", (int)i*10*1.2);
        	 object.put("exclusive_resp_time", i*10);
        	 object.put("payload", 1);
        	 object.put("success_count", (int)(i*10)*0.8);
        	 object.put("count", i*10);
        	 object.put("exception_count", (i*10)-5);
        	 object.put("trace_count", i);
		     producer.send(new ProducerRecord<String, String>("ty-svr-componet-druid-messages", object.toJSONString()));
			 producer.flush();
			 Thread.sleep(1000*60L);*/
			 
			 
			 JSONObject object1 = new JSONObject();
			 object1.put("timestamp", new Date().getTime()/1000);
			 object1.put("data_type", "COMP");//COMP|OP。COMP-组件分解 OP-组件操作分析
			 object1.put("biz_system_id", 1);
			 object1.put("application_id", i%3==0?1:i%3);
			 object1.put("instance_id", i%5==0?1:i%5);
			 object1.put("action_type", "TX");//Action类型：TX|IF|BG
			 object1.put("action_id", i);
			 object1.put("tx_biz_system_id", 1);
			 object1.put("tx_application_id", i%3==0?1:i%3);
			 object1.put("tx_action_id", i%5==0?1:i%5);
			 object1.put("component_type", "Database");
			 object1.put("component_subtype", "MySQL");//Dubbo|Thrift|WebService
			 object1.put("component_name", "192.168.2.1:3306");
			 object1.put("call_mode", 1);
			 object1.put("op_name", "select * from user");
			 object1.put("resp_time", (int)i*10*1.2);
			 object1.put("exclusive_resp_time", i*10);
			 object1.put("payload", 1);
			 object1.put("success_count", (int)(i*10)*0.8);
			 object1.put("count", i*10);
			 object1.put("exception_count", (i*10)-5);
			 object1.put("trace_count", i);
		     producer.send(new ProducerRecord<String, String>("ty-svr-componet-druid-messages", object1.toJSONString()));
			 producer.flush();
			 Thread.sleep(1000*60L);
		  }
        producer.close();
	}

}
