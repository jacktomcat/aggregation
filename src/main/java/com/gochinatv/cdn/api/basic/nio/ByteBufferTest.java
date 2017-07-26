package com.gochinatv.cdn.api.basic.nio;


import java.nio.ByteBuffer;

import org.apache.commons.configuration2.sync.SynchronizerSupport;


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
		buffer.put("fghi".getBytes());
		System.out.println("put之后 capacity:" +buffer.capacity()+" limit:"+buffer.limit()+" position:"+buffer.position()+" remaining:"+buffer.remaining());

		buffer.flip();

		byte[] dest2 = new byte[buffer.limit()];
		buffer.get(dest2);
		System.out.println(new String(dest2));
		System.out.println("flip get 之后 capacity:" +buffer.capacity()+" limit:"+buffer.limit()+" position:"+buffer.position()+" remaining:"+buffer.remaining());

		buffer.position(3); 
		
		buffer.mark();//mark = position
		System.out.println("mark之后 capacity:" +buffer.capacity()+" limit:"+buffer.limit()+" position:"+buffer.position()+" remaining:"+buffer.remaining());
		
		byte b = buffer.get();
		System.out.println(new String(new byte[]{b}));
		System.out.println("mark之后 get一次 capacity:" +buffer.capacity()+" limit:"+buffer.limit()+" position:"+buffer.position()+" remaining:"+buffer.remaining());
		
		buffer.reset();//position = mark
		System.out.println("reset之后 capacity:" +buffer.capacity()+" limit:"+buffer.limit()+" position:"+buffer.position()+" remaining:"+buffer.remaining());
		
		/**
		 * compact()，压缩数据。比如当前EOF是6，当前指针指向2 （即0,1的数据已经写出了，没用了），
		 *	那么compact方法将把2,3,4,5的数据挪到0,1,2,3的位置，
		 *  然后指针指向4的位置。这样的意思是，从4的位置接着再写入数据。
		 *  写完后，把指针挪到0，再写出，然后再compact()，如此反复
		 */
		buffer.compact();// 切换compact写模式    position=(limit-position)   limit=capacity
		System.out.println("compact 之后 capacity:" +buffer.capacity()+" limit:"+buffer.limit()+" position:"+buffer.position()+" remaining:"+buffer.remaining());
	}
	
	
}
