package com.gochinatv.cdn.api.guava.concurrent;

import com.google.common.util.concurrent.Monitor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 源码上这样定义：A synchronization abstraction supporting waiting on arbitrary boolean conditions，
 * 翻译过来就是：一个支持任意布尔条件的同步的抽象。
 * Monitor类是作为ReentrantLock的一个替代，
 * 代码中使用 Monitor比使用ReentrantLock更不易出错，可读性也更强，
 * 并且也没有显著的性能损失，使用Monitor甚至有潜在的性能得到优化。
 * 下面我们整体上对Monitor的源码结构做一下梳理
 * 
 * http://blog.csdn.net/a837199685/article/details/50610141
 *
 * Created by jacktomcat on 17/8/27.
 */

/**
 * 	enter()：进入到当前Monitor，无限期阻塞。
 *	enterInterruptibly()：进入到当前Monitor，无限期阻塞，但可能会被打断。
 *	enter(long time, TimeUnit unit)：进入到当前Monitor，最多阻塞给定的时间，返回是否进入Monitor。
 *	enterInterruptibly(long time, TimeUnit unit)：进入到当前Monitor，最多阻塞给定的时间，但可能会被打断，返回是否进入Monitor。 tryEnter()：如果可以的话立即进入Monitor，不阻塞，返回是否进入Monitor。
 *	enterWhen(Guard guard)：当Guard的isSatisfied()为true时，进入当前Monitor，无限期阻塞，但可能会被打断。
 *	enterWhenUninterruptibly(Guard guard)：当Guard的isSatisfied()为true时，进入当前Monitor，无限期阻塞。
 *	enterWhen(Guard guard, long time, TimeUnit unit)：当Guard的isSatisfied()为true时，进入当前Monitor，最多阻塞给定的时间，这个时间包括获取锁的时间和等待Guard satisfied的时间，但可能会被打断。
 *	enterWhenUninterruptibly(Guard guard, long time, TimeUnit unit)：当Guard的isSatisfied()为true时，进入当前Monitor，最多阻塞给定的时间，这个时间包括获取锁的时间和等待Guard satisfied的时间。
 *	enterIf(Guard guard)：如果Guard的isSatisfied()为true，进入当前Monitor，无限期的获得锁，不需要等待Guard satisfied。
 *	enterIfInterruptibly(Guard guard)：如果Guard的isSatisfied()为true，进入当前Monitor，无限期的获得锁，不需要等待Guard satisfied，但可能会被打断。
 *	enterIf(Guard guard, long time, TimeUnit unit)：如果Guard的isSatisfied()为true，进入当前Monitor，在给定的时间内持有锁，不需要等待Guard satisfied。
 *	enterIfInterruptibly(Guard guard, long time, TimeUnit unit)：如果Guard的isSatisfied()为true，进入当前Monitor，在给定的时间内持有锁，不需要等待Guard satisfied，但可能会被打断。
 *	tryEnterIf(Guard guard)：如果Guard的isSatisfied()为true并且可以的话立即进入Monitor，不等待获取锁，也不等待Guard satisfied。
 *	waitFor(Guard guard)：等待Guard satisfied，无限期等待，但可能会被打断，当一个线程当前占有Monitor时，该方法才可能被调用。
 *	waitForUninterruptibly(Guard guard)：等待Guard satisfied，无限期等待，当一个线程当前占有Monitor时，该方法才可能被调用。
 *	waitFor(Guard guard, long time, TimeUnit unit)：等待Guard satisfied，在给定的时间内等待，但可能会被打断，当一个线程当前占有Monitor时，该方法才可能被调用。
 *	waitForUninterruptibly(Guard guard, long time, TimeUnit unit)：等待Guard satisfied，在给定的时间内等待，当一个线程当前占有Monitor时，该方法才可能被调用。
 *	leave()：离开当前Monitor，当一个线程当前占有Monitor时，该方法才可能被调用。
 *	isFair()：判断当前Monitor是否使用一个公平的排序策略。
 *	isOccupied()：返回当前Monitor是否被任何线程占有，此方法适用于检测系统状态，不适用于同步控制。
 *	isOccupiedByCurrentThread()：返回当前线程是否占有当前Monitor。
 *	getOccupiedDepth()：返回当前线程进入Monitor的次数，如果房前线程不占有Monitor，返回0。
 *	getQueueLength()：返回一个估计的等待进入Monitor的线程数量，只是一个估算值，因为线程的数量在这个方法访问那不数据结构的时候可能会动态改变。此方法适用于检测系统状态，不适用于同步控制。
 *	getWaitQueueLength(Guard guard)：返回一个等待给定Guard satisfied的线程估计数量， 注意，因为超时和中断可能发生在任何时候，所以估计只作为一个等待线程的实际数目的上限。此方法适用于检测系统状态，不适用于同步控制。
 *	hasQueuedThreads()：返回是否有任何线程正在等待进入这个Monitor，注意，因为取消随时可能发生，所以返回true并不保证任何其他线程会进入这个Monitor。此方法设计用来检测系统状态。
 *	hasQueuedThread(Thread thread)：返回给定线程是否正在等待进入这个Monitor，注意，因为取消随时可能发生，所以返回true并不保证给定线程会进入这个Monitor。此方法设计用来检测系统状态。
 *	hasWaiters(Guard guard)：返回是否有任何线程正在等待给定Guard satisfied，注意，因为取消随时可能发生，所以返回true并不保证未来Guard变成satisfied时唤醒任意线程。此方法设计用来检测系统状态。
 * @author jacktomcat
 *
 */
public class MonitorTest {
	
	   private String value;
	   private Monitor monitor = new Monitor();
	    
	   private Monitor.Guard put = new Monitor.Guard(monitor) {
	       @Override
	       public boolean isSatisfied() {
	           return value==null;
	       }
	   };
	   
	   private Monitor.Guard get = new Monitor.Guard(monitor) {
	       @Override
	       public boolean isSatisfied() {
	           return value!=null;
	       }
	   };
	    
	   public void put(String item) throws InterruptedException {
	       monitor.enterWhen(put); //Guard(形如Condition)，不满足则阻塞，而且我们并没有在Guard进行任何通知操作
	       try {
	    	   value = item;
			   System.out.println("进入put方法");
		   } finally {
	           monitor.leave();
	       }
	   }
	   
	   public String get() throws InterruptedException {
		   String result = "";
	       monitor.enterWhen(get); //Guard(形如Condition)，不满足则阻塞，而且我们并没有在Guard进行任何通知操作
	       try {
	    	   result = value;
			   System.out.println("进入get方法");
			   value = null;
	       } finally {
	           monitor.leave();
	       }
	       return result;
	   }

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		MonitorTest test = new MonitorTest();
		for(int i=0; i<100;i++){
			executorService.submit(() -> {
				try {
					test.get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			executorService.submit(() -> {
				try {
					test.put("张三");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		executorService.shutdown();
	}

}
