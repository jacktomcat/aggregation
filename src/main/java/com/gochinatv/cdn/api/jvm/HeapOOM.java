package com.gochinatv.cdn.api.jvm;

/**
 * 
 * @author zhuhuihui
 * -Xms5m -Xmx5m -Xss160k -XX:+PrintGCDetails
 * java -X 非标准输出
 */
public class HeapOOM {
   
	public static void main(String[] args) {
		int number = 0;
		while(true){
			byte[] b = new byte[1024*1024];
			System.out.println(++number);
		}
	}
}
