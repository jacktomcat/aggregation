package com.gochinatv.cdn.api.basic.nio;


import java.nio.ByteBuffer;


/**
 * 参考:
 * http://blog.csdn.net/baple/article/details/12749005
 *
 * http://www.cnblogs.com/lxzh/archive/2013/05/10/3071680.html
 *
 *
 */
public class ByteBufferTest {

	
	public static void main(String[] args) {

		ByteBuffer buffer = ByteBuffer.allocate(10);
		buffer.limit(6);
		System.out.println("初始化 capacity:" +buffer.capacity()+" limit:"+buffer.limit()+" position:"+buffer.position()+" remaining:"+buffer.remaining());

		buffer.put("abcde".getBytes());
		System.out.println("put 字符串 `zhang`之后 capacity:" +buffer.capacity()+" limit:"+buffer.limit()+" position:"+buffer.position()+" remaining:"+buffer.remaining());

		buffer.flip();//一般用于写完成之后的读取操作
		System.out.println("flip 之后 capacity:" +buffer.capacity()+" limit:"+buffer.limit()+" position:"+buffer.position()+" remaining:"+buffer.remaining());

		/**
		 * 读取之前必须flip,否则这里读取的字节数为 6,实际只有5个字节,所以flip之后
		 * limit从之前的6变为position 5, 之前的position从5变为0
		 * 读取的内容为从position到limit之间,刚好就是读取的全部内容
		 */

		/**
		 * 拷贝一个字节数组   (duplicate用法)
		 */
		byte[] dest_copy = new byte[buffer.limit()];
		buffer.duplicate().get(dest_copy);
		System.out.println(new String(dest_copy));

		/**
		 * get用法
		 */
		byte[] dest = new byte[buffer.limit()];
		buffer.get(dest);
		System.out.println(new String(dest));


		System.out.println("clear 之前 capacity:" +buffer.capacity()+" limit:"+buffer.limit()+" position:"+buffer.position()+" remaining:"+buffer.remaining());
		buffer.clear();//position = 0 ; limit = capacity;
		buffer.put("fg".getBytes());
		System.out.println("put之后 capacity:" +buffer.capacity()+" limit:"+buffer.limit()+" position:"+buffer.position()+" remaining:"+buffer.remaining());

		buffer.flip();

		byte[] dest2 = new byte[buffer.limit()];
		buffer.get(dest2);
		System.out.println(new String(dest2));

		buffer.compact();// 切换compact写模式

		//buffer.mark();
		//buffer.reset();

		//System.out.println("clear 之后 capacity:" +buffer.capacity()+" limit:"+buffer.limit()+" position:"+buffer.position()+" remaining:"+buffer.remaining());
		
	}
	
	
}
