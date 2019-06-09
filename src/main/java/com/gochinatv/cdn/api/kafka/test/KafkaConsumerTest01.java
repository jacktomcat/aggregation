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
public class KafkaConsumerTest01 {


    public static void main(String[] args){
        Properties props = new Properties();
        props.put("group.id", "kafka033");
        props.put("bootstrap.servers", Config.BROKERS);
        props.put("enable.auto.commit", "true");
        props.put("auto.offset.reset", "earliest");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        //自动设置offset，消费topic中的全部分区
        consumer.subscribe(Arrays.asList("test1-repartition-messages"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("++++++++++++test01++++++++++++++offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
        }
    }

}
