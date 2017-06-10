package com.gochinatv.cdn.api.protobuf;

import java.io.IOException;
import java.util.List;

import com.chsoft.datacollection.entity.protobuf.UserEntity;
import com.chsoft.datacollection.entity.protobuf.UserEntity.User;
import com.chsoft.datacollection.entity.protobuf.UserEntity.Users;
import com.google.protobuf.InvalidProtocolBufferException;

public class ProtoBufListTest {

	public static void main(String[] args) throws IOException {
		byte[] data = write();
		//read(data);
	}

	public static byte[] write() {
		// 模拟将对象转成byte[]，方便传输
		UserEntity.Users.Builder users = UserEntity.Users.newBuilder();
		long start = System.currentTimeMillis();
		for(int i=0;i<10000;i++){
			UserEntity.User.Builder builder = UserEntity.User.newBuilder();
			builder.setId(2);
			builder.setUserName("张三" + i);
			builder.setAge(30);
			builder.setPhone("13439259710");
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
