package com.gochinatv.cdn.api.jdk.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

/**
 * http://ifeve.com/concurrency-cyclicbarrier/
 * 
 * @author jacktomcat
 * 
 *         CyclicBarrier 的字面意思是可循环使用（Cyclic）的屏障（Barrier）。
 *         它要做的事情是，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活。
 *         CyclicBarrier默认的构造方法是CyclicBarrier(int parties)，其参数表示屏障拦截的线程数量，
 *         每个线程调用await方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞。
 *
 * CyclicBarrier和CountDownLatch的区别
 *  1: CountDownLatch的计数器只能使用一次。而CyclicBarrier的计数器可以使用reset() 方法重置。所以CyclicBarrier能处理更为复杂的业务场景，比如如果计算发生错误，可以重置计数器，并让线程们重新执行一次。
 *  
 *	2: CyclicBarrier还提供其他有用的方法，比如getNumberWaiting方法可以获得CyclicBarrier阻塞的线程数量。isBroken方法用来知道阻塞的线程是否被中断。比如以下代码执行完之后会返回true。
 * 
 */
public class CyclicBarrierTest {

	static CyclicBarrier c = new CyclicBarrier(2);
	static CyclicBarrier action = new CyclicBarrier(2,new ActionThread());

	/**
	 *  如果把new CyclicBarrier(2)修改成new CyclicBarrier(3)则主线程和子线程会永远等待，
	 *  因为没有第三个线程执行await方法，即没有第三个线程到达屏障，所以之前到达屏障的两个线程都不会继续执行。
	 */
	@Test
	public void cyclicBarrierAwait() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1000L);
					System.out.println("子线程等待" + Thread.currentThread().getName());
					int numberWaiting = c.getNumberWaiting();
					boolean broken = c.isBroken();
					System.out.println("等待屏障个数:" + numberWaiting + ",当前线程是否中断:" + broken);
					c.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();

		try {
			System.out.println("主线程线程等待" + Thread.currentThread().getName());
			c.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("两个屏障被解除");

		c.reset();//reset 之后可以重复使用
		
		try {
			try {
				c.await(1000,TimeUnit.MILLISECONDS);//设置超时
				c.await(1000,TimeUnit.MILLISECONDS);//设置超时
			} catch (TimeoutException e) {
				System.out.println("等待超时:"+e.getMessage()+",需要自己处理");
				//e.printStackTrace();
			}//这里是重复使用的
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println("重新设置屏障之后");
	}
	
	
	/**
	 * CyclicBarrier还提供一个更高级的构造函数CyclicBarrier(int parties, Runnable barrierAction)，
	 *  用于在线程到达屏障时，优先执行barrierAction，方便处理更复杂的业务场景
	 */
	@Test
	public void cyclicBarrierAwaitAction() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000L);
					System.out.println("子线程等待" + Thread.currentThread().getName());
					action.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();

		try {
			System.out.println("主线程线程等待" + Thread.currentThread().getName());
			action.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("======运行结束====");
	}
	
}

/**
 * 
 * 优先执行ActionThread，方便处理更复杂的业务场景
 * @author jacktomcat
 *
 */
class ActionThread implements Runnable{

	@Override
	public void run() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("===所有屏障解除之后,执行这个方法===");
	}
	
}
