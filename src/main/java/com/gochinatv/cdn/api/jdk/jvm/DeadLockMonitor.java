package com.gochinatv.cdn.api.jdk.jvm;


/**
 * http://blog.csdn.net/yohoph/article/details/42041729
 * http://www.cnblogs.com/wade-xu/p/4369094.html
 * 
 * 1: 死锁通过visualvm监控 查看线程dump信息
 * 2: 如果远程不好调试,通过使用jstack -l pid
 * 
 * @author jacktomcat
 *
 */
public class DeadLockMonitor {

	static Object product = new Object();
	static Object consumer = new Object();

	public static void main(String[] args) {

		Thread thread = new Thread(() -> {
			synchronized (product) {
				try {
					Thread.sleep(5000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"获得product锁对象");
				synchronized (consumer) {
					System.out.println(Thread.currentThread().getName()+"获得consumer锁对象");
				}
			}
		}, "线程producer");
		thread.start();

		Thread thread2 = new Thread(() -> {
			synchronized (consumer) {
				System.out.println(Thread.currentThread().getName()+"获得consumer锁对象");
				synchronized (product) {
					System.out.println(Thread.currentThread().getName()+"获得product锁对象");
				}
			}
			
		}, "线程consumer");
		thread2.start();
		
	}
}
