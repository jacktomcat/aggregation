package com.gochinatv.cdn.api.kafka;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;

import com.google.common.collect.Lists;


/**
 * 分区的方式进行生产
 * @author jacktomcat
 *
 */
public class KafkaPartitionConsumerTest {
   
	
	
	public static void main(String[] args) throws Exception {
		
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		
		Properties props = new Properties();
        props.put("group.id", "test02");
        //props.put("zookeeper.connect", Config.ZK);
        props.put("bootstrap.servers", Config.BROKERS);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);


        /**
         * 只消费一个topic分区中的部分分区
         */
	     TopicPartition partition0 = new TopicPartition("spark-test-proc", 0);
	     TopicPartition partition1 = new TopicPartition("spark-test-proc", 1);
	     TopicPartition partition2 = new TopicPartition("spark-test-proc", 2);
	     TopicPartition partition3 = new TopicPartition("spark-test-proc", 3);
	     
	     List<TopicPartition> partitions = Lists.newArrayList(partition0,partition1,partition2,partition3);
	     consumer.assign(partitions);
	    
	    	 executorService.submit(()->{
	    		 while (true) {
	    	         ConsumerRecords<String, String> records = consumer.poll(100);
	    	         for (ConsumerRecord<String, String> record : records){
	    	             System.out.printf(Thread.currentThread().getName()+ "offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
	    	         }
	    	     }
		     });
	    	 
	    	 /*executorService.submit(()->{
	    		 while (true) {
	    	         ConsumerRecords<String, String> records = consumer.poll(100);
	    	         for (ConsumerRecord<String, String> record : records){
	    	             System.out.printf(Thread.currentThread().getName()+ "offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
	    	         }
	    	     }
		     });*/
		 
	}
	
}
