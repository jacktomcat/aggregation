package com.gochinatv.cdn.api.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * 分区的方式进行生产
 * 
 * @author jacktomcat
 *
 */
public class KafkaPartitionProducerTest {

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
		int counter = 0;
		// case 3
		// 需要使用命令行创建topic并且指定分区数，同时发送消息至分区
		for (int i=0; i < 100; i++) {
			//if(counter+1%100==0)
			//	Thread.sleep(1000L);

			//producer.send(new ProducerRecord("spark-test-proc","zhuhh-"+i));
			int partition = (i%4);
			producer.send(new ProducerRecord<String, String>("spark-test-proc1", partition, Integer.toString(i), "partition-"+partition+"-jackjboss-"+i));
			counter = counter + 1;
			//producer.flush();
			System.out.println("发送消息成功");
		}
		producer.flush();
		producer.close();
	}

}
