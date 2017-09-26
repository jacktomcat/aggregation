package com.gochinatv.cdn.api.jdk.jvm;


/**
 * 通过visualvm监控
 *
 * http://blog.csdn.net/yohoph/article/details/42041729
 * http://www.cnblogs.com/wade-xu/p/4369094.html
 * 
 * -XX:+PrintGCDetails 开关，可以详细了解GC中的变化
 * -XX:+PrintGCTimeStamps 开关，可以了解这些垃圾收集发生的时间，自JVM启动以后以秒计量
 * -XX:+PrintGCDateStamps GC发生的时间信息
 * -XX:+PrintHeapAtGC 开关了解堆的更详细的信息
 * -XX:+HeapDumpOnOutOfMemoryError 堆内存溢出的时候打印错误的堆信息
 * -XX:HeapDumpPath 堆内存溢出打印日志的位置,比如输出的地址为 /Users/zhuhuihui 文件名为java的pid.hprof
 *
 * java -X 非标准输出
 * 
 * jdk7:
 * -Xms20m -Xmx20m -Xss160k -XX:NewSize=5M -XX:MaxNewSize=6M -XX:PermSize=100M -XX:+PrintHeapAtGC -XX:+PrintGCTimeStamps -XX:+PrintGCDetails 
 * -XX:+PrintGCDateStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:/logs
 *
 * jdk8:
 * -Xms20m -Xmx20m -Xss160k -XX:NewSize=5M -XX:MaxNewSize=6M -XX:MetaspaceSize=100M -XX:+PrintHeapAtGC -XX:+PrintGCTimeStamps -XX:+PrintGCDetails 
 * -XX:+PrintGCDateStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:/logs
 * 
 */

/**
 * jdk8 已经移除了 PermSize,MaxPermSize 永久区属性参数
 * 
 * Java HotSpot(TM) 64-Bit Server VM warning: ignoring option PermSize=100M; support was removed in 8.0
 * Java HotSpot(TM) 64-Bit Server VM warning: ignoring option MaxPermSize=120M; support was removed in 8.0
 */
public class HeapOOM2 {
   
	static StringBuffer buffer = new StringBuffer();
	public static void main(String[] args) {
		while(true){
			buffer.append("测试,");//最终在内存空间中存储的是char[]数组,通过底层源码也可以看出来是char[]
			System.out.println(buffer.length());
		}
	}
	
}

