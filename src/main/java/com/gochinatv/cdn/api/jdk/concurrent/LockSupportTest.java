package com.gochinatv.cdn.api.jdk.concurrent;

import java.util.concurrent.locks.LockSupport;

import org.junit.Test;

/**
 * 锁支持
 * http://www.jb51.net/article/88395.htm
 * 
 * LockSupport类是Java6(JSR166-JUC)引入的一个类，提供了基本的线程同步原语。LockSupport实际上是调用了Unsafe类里的函数，归结到Unsafe里，只有两个函数：
 * 
 * LockSupport.park(Thread thread)，//阻塞thread
 * LockSupport.unpark(Thread thread) //唤醒thread
 *   
 * 这个类有着wait()，notify()类似的功能，不过更精准
 * 
 * 说明：park和wait的区别。wait让线程阻塞前，必须通过synchronized获取同步锁
 * 
 * @author jacktomcat
 *
 */
public class LockSupportTest {

	
	/**
	 * 可以发现主线程一直处于阻塞状态。因为 许可默认是被占用的 ，调用park()时获取不到许可，所以进入阻塞状态。
	 */
	@Test
	public void park() {
		LockSupport.park();
		System.out.println("block.");
	}
	
	
	@Test
	public void unparkAndPark() {
		Thread thread = Thread.currentThread();
		//因为这里默认是占用的许可,所以必须先unpark
	    LockSupport.unpark(thread);//释放许可
	    LockSupport.park();// 获取许可
	    System.out.println("unpack and park");
	}
	
	
	@Test
	public void childrenThreadAndMainThread() {
		Thread thread = Thread.currentThread();
		System.out.println("主线程start");
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				 System.out.println("子线程释放许可unpark");//因为这里默认是占用的许可,所以必须先unpark
				 LockSupport.unpark(thread);//释放许可  
				 //一定要注意这里的unpark(thread) thread而不是  Thread.currentThread(),因为释放许可和获取许可应该是针对同一个线程
			}
		}, "").start();
		
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("主线程获得许可");
	    LockSupport.park(thread);// 获取许可
	    System.out.println("主线程end");
	}
	
}
