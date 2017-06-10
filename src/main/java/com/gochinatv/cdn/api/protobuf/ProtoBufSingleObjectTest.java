package com.gochinatv.cdn.api.protobuf;

import java.io.IOException;

import com.chsoft.datacollection.entity.protobuf.UserEntity;
import com.chsoft.datacollection.entity.protobuf.UserEntity.User;
import com.google.protobuf.InvalidProtocolBufferException;

public class ProtoBufSingleObjectTest {

	public static void main(String[] args) throws IOException {
		byte[] data = write();
		read(data);
	}

	public static byte[] write() {
		// 模拟将对象转成byte[]，方便传输
		UserEntity.User.Builder builder = UserEntity.User.newBuilder();
		builder.setId(1);
		builder.setUserName("张三");
		builder.setAge(10);
		// builder.setPhone("");
		builder.setAddress("北京市");

		UserEntity.User write = builder.build();

		System.out.println(write.toByteString());
		// 模拟接收Byte[]，反序列化成Person类
		
		byte[] byteArray = write.toByteArray();
		return byteArray;
	}

	public static void read(byte[] data) {
		User user = null;
		try {
			user = User.parseFrom(data);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		System.out.println("id :" + user.getId());
		System.out.println("userName :" + user.getUserName());
		System.out.println("age :" + user.getAge());
		System.out.println("phone :" + user.getPhone());
		System.out.println("address :" + user.getAddress());
	}

}
