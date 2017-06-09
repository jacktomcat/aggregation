package com.gochinatv.cdn.api.avro;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

/**
 * 第一种方式：
 *引用jar包的形式，video.avsc 在jar中的 classpath下面的avro文件夹下，jar文件中并不用生成好的video类，而是用avsc
 *文件映射
 *
 */
public class SerializeUser4 {
   
	public static void main(String[] args) throws Exception {
		
		InputStream inputStream = ClassLoader.getSystemResourceAsStream("avro/video.avsc");  
		Schema schema = new Schema.Parser().parse(inputStream);
		
		byte[] data = write(schema);
		read(schema,data);
	}
	
	
	
	/**
	 * 序列化
	 * @param file
	 * @throws Exception
	 */
	public static byte[] write(Schema schema) throws Exception{
		//序列化 到内存字节数组 
		DatumWriter<GenericRecord> userDatumWriter = new SpecificDatumWriter<GenericRecord>(schema); 
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		BinaryEncoder binaryEncoder = EncoderFactory.get().binaryEncoder(outputStream, null);
		for(int i=0;i<10000;i++){
			GenericRecord video = new GenericData.Record(schema);  
			video.put("id",2);  
			video.put("videoName", "爱情保卫战" + i); 
			video.put("desc", "13439259710");
			video.put("status",1); 
			userDatumWriter.write(video, binaryEncoder);
		}
		binaryEncoder.flush();//带缓冲区的binaryEncoder和直接directBinaryEncoder不一样，需要flush一下，否则字节数组没有数据
		outputStream.flush();
		outputStream.close();
		byte[] data = outputStream.toByteArray();
		System.out.println(data.length+"==");
		return data;
	}
	
	
	/**
	 * 反序列化
	 * @param file
	 * @throws IOException
	 */
	public static void read(Schema schema,byte[] data) throws IOException{
		DatumReader<GenericRecord> userDatumReader = new SpecificDatumReader<GenericRecord>(schema); 
		BinaryDecoder binaryDecoder = DecoderFactory.get().binaryDecoder(new ByteArrayInputStream(data), null);
		while(!binaryDecoder.isEnd()){
			GenericRecord record = userDatumReader.read(null, binaryDecoder);
			String videoName = record.get("videoName").toString();
			System.out.println(videoName);
		}
	}
	
	
}
