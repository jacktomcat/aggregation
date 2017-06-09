package com.gochinatv.cdn.api.avro;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 * jvm序列化和avro序列号对比，结果jvm占用的存储空间大于avro
 * @author jack
 * 
 * 总结：
 *  1）Java序列化就是把对象转换成字节序列，而Java反序列化就是把字节序列还原成Java对象。
 *  2）采用Java序列化与反序列化技术，一是可以实现数据的持久化，在MVC模式中很是有用；二是可以对象数据的远程通信。
 *
 */
public class JavaSerialize{

	public static void main(String[] args) throws Exception {
		//File file = new File("/Users/zhuhuihui/users.object");
		//FileOutputStream fos = new FileOutputStream(file); 
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);  
		   
		   for(int i=0;i<10000;i++){
			   Users user = new Users(1,"张三",30,"13439259710");
			   oos.writeObject(user);  
		   }
		   oos.flush();  
		   oos.close();
		   baos.flush();
		   baos.close();
		   byte[] byteArray = baos.toByteArray();
		   System.out.println(byteArray.length);
		   
		
	}
	
}


class Users implements Serializable{
	
private int id;
	
	private String userName;
	
	private int age;
	
	private String phone;

	public Users(){}
	
	public Users(int id,String userName,int age,String phone){
		this.id = id;
		this.userName = userName;
		this.age = age;
		this.phone = phone;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
