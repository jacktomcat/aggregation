package com.gochinatv.cdn.api.kafka.test;

import com.gochinatv.cdn.api.kafka.Config;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * @author jack
 *         查看消费情况
 *         kafka-run-class.bat kafka.admin.ConsumerGroupCommand  --bootstrap-server localhost:9092  --describe --group kafka01
 * @author zhuhh
 */
public class KafkaConsumerTest {


    public static void main(String[] args){
        Properties props = new Properties();
        props.put("group.id", "kafka02");
        //props.put("zookeeper.connect", Config.ZK);
        props.put("bootstrap.servers", Config.BROKERS);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //props.put("session.timeout.ms", "5000");
        //props.put("request.timeout.ms", "15000");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        //自动设置offset，消费topic中的全部分区
        consumer.subscribe(Arrays.asList("test1-repartition-messages"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("============================offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //测试手动设置offset
         /*consumer.subscribe(Arrays.asList("kafka-test"));
	     while (true) {
	         ConsumerRecords<String, String> records = consumer.poll(100);
	         for (ConsumerRecord<String, String> record : records){
	             System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
	         }
	         //下面设置后，导致消费端重复消费，如果不指定offset那么消费之后默认移动offset的位置
	         TopicPartition partition = new TopicPartition("kafka-test", 0);
	         consumer.seek(partition, 9911063);
	     }*/


        /**
         * 只消费一个topic分区中的部分分区
         */
//	     TopicPartition partition0 = new TopicPartition("spark-test", 0);
//	     TopicPartition partition1 = new TopicPartition("spark-test", 1);
//	     TopicPartition partition2 = new TopicPartition("spark-test", 2);
//	     consumer.assign(Arrays.asList(partition0,partition1,partition2));
//
//	     while (true) {
//	         ConsumerRecords<String, String> records = consumer.poll(100);
//	         for (ConsumerRecord<String, String> record : records){
//	             System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
//	         }
//	     }

    }

}
