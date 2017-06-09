package com.gochinatv.cdn.api.avro;

import java.io.File;
import java.io.IOException;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import com.chsoft.datacollection.entity.User;


/**
 * http://shift-alt-ctrl.iteye.com/blog/2217425
 * http://blog.csdn.net/caomiao2006/article/details/51588927
 * 第一种方式：
 * 1: java -jar avro-tools-1.8.1.jar compile schema user.avsc ./ 
 * 2: 生成的User.java 拷贝到本目录下面
 * 3:把序列化的对象放入到 users.avro 磁盘文件中  没用使用任何的编码方式
 * @author jack
 *
 */
public class SerializeUser{
   
	public static void main(String[] args) throws Exception {
		
		//序列化  
		File diskFile = new File("/Users/zhuhuihui/users.avro");  
		write(diskFile);
		read(diskFile);
	}
	
	/**
	 * 序列化
	 * @param file
	 * @throws Exception
	 */
	public static void write(File file) throws Exception{
		
		DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class); 
		
		DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);  
		//指定schema  
		dataFileWriter.create(User.getClassSchema(), file);  
		
		for(int i=0;i<10000;i++){
			User user = new User();  
			user.setId(1);
			user.setUserName("张三");
			user.setAge(30);  
			user.setPhone("13439259710");
			dataFileWriter.append(user);  
			dataFileWriter.fSync();//多次写入之后，可以调用fsync将数据同步写入磁盘(IO)通道 
		}
		dataFileWriter.close(); 
	}
	
	
	/**
	 * 反序列化
	 * @param file
	 * @throws IOException
	 */
	public static void read(File file) throws IOException{
		//反序列化  
		DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);  
		// 也可以使用DataFileStream  
		// DataFileStream<User> dataFileStream = new DataFileStream<User>(new FileInputStream(diskFile),userDatumReader);  
		DataFileReader<User> dataFileReader = new DataFileReader<User>(file, userDatumReader);  
		User _current = null;  
		while (dataFileReader.hasNext()) {  
		  
		    //注意:avro为了提升性能，_current对象只会被创建一次，且每次遍历都会重用此对象  
		    //next方法只是给_current对象的各个属性赋值，而不是重新new。  
		    _current = dataFileReader.next(_current);  
		    //toString方法被重写，将获得JSON格式  
		    System.out.println(_current.getId()+":"+_current.getUserName());  
		}  
		dataFileReader.close(); 
	}
	
}
