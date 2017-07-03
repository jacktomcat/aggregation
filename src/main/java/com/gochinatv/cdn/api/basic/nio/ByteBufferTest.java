package com.gochinatv.cdn.api.basic.nio;

import java.nio.ByteBuffer;


public class ByteBufferTest {
   
	
	public static void main(String[] args) {
		
		ByteBuffer buffer = ByteBuffer.allocate(5);
		System.out.println("初始化 capacity:" +buffer.capacity()+" limit:"+buffer.limit()+" position:"+buffer.position()+" remaining:"+buffer.remaining());
		buffer.put("abcde".getBytes()); 
		System.out.println("put 字符串 `zhang`之后 capacity:" +buffer.capacity()+" limit:"+buffer.limit()+" position:"+buffer.position()+" remaining:"+buffer.remaining());
		buffer.flip();
		System.out.println("flip 之后 capacity:" +buffer.capacity()+" limit:"+buffer.limit()+" position:"+buffer.position()+" remaining:"+buffer.remaining());
		//buffer.compact();// 切换compact写模式
		//buffer.put("d".getBytes());
		byte[] dest_copy = new byte[buffer.limit()];
		buffer.duplicate().get(dest_copy);
		System.out.println(new String(dest_copy));
		
		byte[] dest = new byte[buffer.limit()];
		buffer.get(dest, 0, buffer.limit());
		System.out.println(new String(dest));
		
		System.out.println(buffer.get());
		
		//buffer.clear();
		//buffer.mark();
	    
		//System.out.println("clear 之后 capacity:" +buffer.capacity()+" limit:"+buffer.limit()+" position:"+buffer.position()+" remaining:"+buffer.remaining());
		
		//buffer.put("springtyp".getBytes()); 
	}
	
	
}
