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
 * http://shift-alt-ctrl.iteye.com/blog/2217425
 * http://blog.csdn.net/caomiao2006/article/details/51588927
 * @author jack
 *
 */
public class SerializeUser3 {
   
	public static void main(String[] args) throws Exception {
		
		InputStream inputStream = ClassLoader.getSystemResourceAsStream("user.avsc");  
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
		//BinaryEncoder binaryEncoder = EncoderFactory.get().directBinaryEncoder(outputStream, null);
		BinaryEncoder binaryEncoder = EncoderFactory.get().binaryEncoder(outputStream, null);
		for(int i=0;i<10000;i++){
			GenericRecord user = new GenericData.Record(schema);  
			user.put("id",2);  
			user.put("userName", "张三" + i);  
			user.put("age",30); 
			user.put("phone", "13439259710");
			userDatumWriter.write(user, binaryEncoder);
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
		//BinaryDecoder binaryDecoder = DecoderFactory.get().directBinaryDecoder(new ByteArrayInputStream(data), null);
		BinaryDecoder binaryDecoder = DecoderFactory.get().binaryDecoder(new ByteArrayInputStream(data), null);
		while(!binaryDecoder.isEnd()){
			GenericRecord u = userDatumReader.read(null, binaryDecoder);
			System.out.println(u.get("userName"));
		}
	}
}
