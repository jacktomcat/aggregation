package com.gochinatv.cdn.api.protobuf;

import java.io.IOException;

import com.chsoft.datacollection.entity.protobuf.UserEntity;
import com.chsoft.datacollection.entity.protobuf.UserEntity.User;
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
