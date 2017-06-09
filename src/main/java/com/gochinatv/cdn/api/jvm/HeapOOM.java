package com.gochinatv.cdn.api.jvm;

/**
 * 
 * @author jack
 * -Xms2m -Xmx2m -Xss160k -XX:+PrintGCDetails
 * java -X 非标准输出
 */
public class HeapOOM {
   
	public static void main(String[] args) {
		int number = 0;
		while(true){
			byte[] b = new byte[1024*1024*5];
			System.out.println(++number);
		}
	}
}
