package com.gochinatv.cdn.api.jdk.concurrent;


/**
 * 锁支持
 * 
 * LockSupport类是Java6(JSR166-JUC)引入的一个类，提供了基本的线程同步原语。LockSupport实际上是调用了Unsafe类里的函数，归结到Unsafe里，只有两个函数：
 * 
 * LockSupport.park(Thread thread)，//阻塞thread
 * LockSupport.unpark(Thread thread) //唤醒thread
 *   
 * 这个类有着wait()，notify()类似的功能，不过更精准
 * @author jacktomcat
 *
 */
public class LockSupportTest {

	public static void main(String[] args) {
		
		
	}
}
