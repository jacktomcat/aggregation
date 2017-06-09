package com.gochinatv.cdn.api.avro;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;


/**
 * http://shift-alt-ctrl.iteye.com/blog/2217425
 * http://blog.csdn.net/caomiao2006/article/details/51588927
 * 第二种方式：
 * 1:不需要像第一种方式那样生成java文件，直接从avsc中读取scheme信息，但是下面要put字段
 * 2:把文件存储在user.avro 磁盘文件中，第一种和这一种没有用任何的编码方式
 * @author jack
 *
 */
public class SerializeUser2 {
   
	public static void main(String[] args) throws Exception {
		
		File diskFile = new File("/Users/zhuhuihui/users.avro"); 
		
		//这里做兼容性测试，user.avsc定义文件中多了一个address属性
		InputStream writeStream = ClassLoader.getSystemResourceAsStream("user.avsc");  
		Schema writeSchema = new Schema.Parser().parse(writeStream);
		write(writeSchema,diskFile);
		
		//解析的时候没有address属性，这里不会报错
		InputStream readStream = ClassLoader.getSystemResourceAsStream("user-v2.avsc");  
		Schema readSchema = new Schema.Parser().parse(readStream);
		read(readSchema,diskFile);
	}
	
	/**
	 * 序列化
	 * @param file
	 * @throws Exception
	 */
	public static void write(Schema schema,File file) throws Exception{
		GenericRecord user = new GenericData.Record(schema);  
		user.put("id",2);  
		user.put("userName", "张三");  
		user.put("age",30); 
		//user.put("phone", null);
		user.put("address", "北京市");
		
		//序列化  
		DatumWriter<GenericRecord> userDatumWriter = new SpecificDatumWriter<GenericRecord>(schema); 
		
		DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(userDatumWriter);  
		//指定schema  
		dataFileWriter.create(schema, file);  
		
		dataFileWriter.append(user);  
		dataFileWriter.fSync();//多次写入之后，可以调用fsync将数据同步写入磁盘(IO)通道 
		user.put("id",3);  
		user.put("userName", "李四");  
		user.put("age",34); 
		user.put("phone", "13439259710");
		user.put("address", "北京市");
		dataFileWriter.append(user);  
		dataFileWriter.close(); 
	}
	
	
	/**
	 * 反序列化
	 * @param file
	 * @throws IOException
	 */
	public static void read(Schema schema,File file) throws IOException{
		//反序列化  
		DatumReader<GenericRecord> userDatumReader = new SpecificDatumReader<GenericRecord>(schema);  
		// 也可以使用DataFileStream  
		// DataFileStream<User> dataFileStream = new DataFileStream<User>(new FileInputStream(diskFile),userDatumReader);  
		DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(file, userDatumReader);  
		GenericRecord _current = null;  
		while (dataFileReader.hasNext()) {  
		  
		    //注意:avro为了提升性能，_current对象只会被创建一次，且每次遍历都会重用此对象  
		    //next方法只是给_current对象的各个属性赋值，而不是重新new。  
		    _current = dataFileReader.next(_current);  
		    //toString方法被重写，将获得JSON格式  
		    System.out.println(_current); 
		}  
		dataFileReader.close(); 
	}
}
