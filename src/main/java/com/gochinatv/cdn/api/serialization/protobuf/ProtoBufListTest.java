package com.gochinatv.cdn.api.serialization.protobuf;

import java.io.IOException;
import java.util.List;

import com.chsoft.datacollection.entity.protobuf.UserEntity;
import com.chsoft.datacollection.entity.protobuf.UserEntity.User;
import com.chsoft.datacollection.entity.protobuf.UserEntity.Users;
import com.google.protobuf.InvalidProtocolBufferException;


/**
 * protoc --java_out=./ user-entity.proto
 * 
 * ProtoBuf序列化后只有两个字节，跟前面两种简直没法比，但ProtoBuf和其他两种的序列化区别很大，
 * ProtoBuf为与语言平台无关的，需要编写自定义的proto文件定义数据格式，
 * 然后用ProtoBuf编译器编译成目标语言：C++、Java、Python等的代理类，对象的序列化、反序列化都需要使用该类来完成；
 * 
 * ProtoBuf序列化对象的byte数组由一系列的key-Value组成，
 * key计算公式为：(field_number<<3)|wire_type、Value：经过编码后的byte，
 * ProtoBuf使用了Varint、zigzag编码极大的压缩了序列化后byte数组的大小，
 * 所以当前我们看到的byte数组只有08 08 两个字节。
 * 
 * 
 * @author zhuhuihui
 *
 */

public class ProtoBufListTest {

	public static void main(String[] args) throws IOException {
		byte[] data = write();
		read(data);
	}

	public static byte[] write() {
		// 模拟将对象转成byte[]，方便传输
		UserEntity.Users.Builder users = UserEntity.Users.newBuilder();
		long start = System.currentTimeMillis();
		for(int i=0;i<10000;i++){
			UserEntity.User.Builder builder = UserEntity.User.newBuilder();
			builder.setId(i);
			builder.setUserName("张三" + i);
			builder.setAge(30);
			builder.setPhone("13139259711");
			builder.setAddress("北京市");
			
			UserEntity.User write = builder.build();
			users.addUser(write);
		}
		byte[] data = users.build().toByteArray();
		System.out.println(data.length+"============耗时："+(System.currentTimeMillis()-start));
		return data;
	}

	public static void read(byte[] data) {
		Users users = null;
		try {
			users = Users.parseFrom(data);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		List<User> userList = users.getUserList();
		for (User user : userList) {
			System.out.println("id :" + user.getId());
			System.out.println("userName :" + user.getUserName());
			System.out.println("age :" + user.getAge());
			System.out.println("phone :" + user.getPhone());
			System.out.println("address :" + user.getAddress());
		}
	}

}
