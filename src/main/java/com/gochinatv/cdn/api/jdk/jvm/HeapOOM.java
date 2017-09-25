package com.gochinatv.cdn.api.jdk.jvm;

/**
 *
 * http://blog.csdn.net/yohoph/article/details/42041729
 *
 * -XX:+PrintGCDetails 开关，可以详细了解GC中的变化
 * -XX:+PrintGCTimeStamps 开关，可以了解这些垃圾收集发生的时间，自JVM启动以后以秒计量
 * -XX:+PrintGCDateStamps GC发生的时间信息
 * -XX:+PrintHeapAtGC 开关了解堆的更详细的信息
 * -XX:+HeapDumpOnOutOfMemoryError 堆内存溢出的时候打印错误的堆信息
 * -XX:HeapDumpPath 堆内存溢出打印日志的位置,比如输出的地址为 /Users/zhuhuihui 文件名为java的pid.hprof
 *
 *
 * @author jack
 * -Xms10m -Xmx10m -Xss160k -XX:NewSize=5m -XX:+PrintHeapAtGC -XX:+PrintGCTimeStamps -XX:+PrintGCDetails -XX:+PrintGCDateStamps
 *  -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/zhuhuihui
 *
 * java -X 非标准输出
 */
public class HeapOOM {
   
	public static void main(String[] args) {
		int number = 0;
		while(true){
			byte[] b = new byte[1024*1024*20];
			System.out.println(b.length);
		}
	}
}
