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
public class KafkaProducerTopo {
   
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
        	 JSONObject object = new JSONObject();
        	 object.put("timestamp", new Date().getTime()/1000);
        	 object.put("data_type", "BIZD");   //APP|BIZ|TX。APP-应用拓扑 BIZ-业务系统拓扑 TX-事务拓扑
        	 object.put("biz_system_id", 1);
        	 object.put("application_id", i%3==0?1:i%3);
        	 object.put("instance_id", i%5==0?1:i%5);
        	 object.put("action_id", i);
        	 object.put("caller_biz_system_id", 1);
        	 object.put("caller_application_id", i%2==0?1:i%2);
        	 object.put("caller_action_id", i%4==0?1:i%4);
        	 object.put("caller_action_type", "IF"); //TX|IF
        	 object.put("callee_biz_system_id", 1);
        	 object.put("callee_application_id", i%4==0?1:i%4);
        	 object.put("callee_action_id", i);
        	 object.put("callee_action_type", "TX"); //TX|IF
        	 object.put("resp_time", (int)i*20*0.8);
        	 object.put("success_count", (int)i*10*0.8);
        	 object.put("count", i*10);
        	 System.out.println(object.toJSONString());
		     producer.send(new ProducerRecord<String, String>("ty-svr-app-topo-druid-messages", object.toJSONString()));
			 producer.flush();
			 
			 Thread.sleep(1000*60L);
			
		 }
     producer.close();
	}

}
