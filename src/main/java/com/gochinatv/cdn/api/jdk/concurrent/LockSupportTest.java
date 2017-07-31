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
	
	
	/**
	 * synchronized 中的wait和notify,必须先wait,然后在notify
	 */
	@Test
	public void waitAndNotify() {
		Thread main = new Thread(new Runnable() {
			@Override
			public void run() {
				
			}
		});
		System.out.println("主线程启动===="+main.getName());
		main.start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(5000L);
					System.out.println("子线程唤醒主线程notify===="+Thread.currentThread().getName());
					synchronized (main) {
						main.notify();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		System.out.println("主线程等待wait===="+main.getName());
		try {
			Thread.sleep(1000L);
			synchronized (main) {
				main.wait();   //如果当前线程不是对象所得持有者，该方法抛出一个java.lang.IllegalMonitorStateException 异常
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("主线程结束===="+main.getName());
	}
	
	//常用方法汇总
	/**返回提供给最近一次尚未解除阻塞的 park 方法调用的 blocker 对象，如果该调用不受阻塞，则返回 null。**/
	//static Object getBlocker(Thread t)
	
	/**为了线程调度，禁用当前线程，除非许可可用。**/
	//static void park()
	
	/**为了线程调度，在许可可用之前禁用当前线程。**/
	//static void park(Object blocker)
	
	/**为了线程调度禁用当前线程，最多等待指定的等待时间，除非许可可用。**/
	//static void parkNanos(long nanos)
	
	/**为了线程调度，在许可可用前禁用当前线程，并最多等待指定的等待时间。**/
	//static void parkNanos(Object blocker, long nanos)
	
	/**为了线程调度，在指定的时限前禁用当前线程，除非许可可用。**/
	//static void parkUntil(long deadline)
	
	/**为了线程调度，在指定的时限前禁用当前线程，除非许可可用。**/
	//static void parkUntil(Object blocker, long deadline)
	
	/**如果给定线程的许可尚不可用，则使其可用。**/
	//static void unpark(Thread thread)
	
}
