package com.gochinatv.cdn.api.jdk.concurrent;


import java.util.concurrent.CountDownLatch;

import org.junit.Test;

/**
 * http://www.importnew.com/15731.html
 * 
 * @author jacktomcat
 * 
 * 在实时系统中的使用场景
 * 1. 实现最大的并行性：有时我们想同时启动多个线程，实现最大程度的并行性。例如，我们想测试一个单例类。
 *     如果我们创建一个初始计数为1的CountDownLatch，并让所有线程都在这个锁上等待，那么我们可以很轻松地完成测试。我们只需调用 一次countDown()方法就可以让所有的等待线程同时恢复执行。
 * 2. 开始执行前等待n个线程完成各自任务：例如应用程序启动类要确保在处理用户请求前，所有N个外部系统已经启动和运行了。
 * 3. 死锁检测：一个非常方便的使用场景是，你可以使用n个线程访问共享资源，在每次测试阶段的线程数目是不同的，并尝试产生死锁。
 *
 */
public class CountDownLatchTest {
	
	@Test
	public void latch() {
		CountDownLatch latch = new CountDownLatch(3);
		System.out.println("=======主线程 ["+Thread.currentThread().getName()+"] 分配任务开始====");
		try {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(2000L);
						latch.countDown();
						System.out.println("=======子线程 ["+Thread.currentThread().getName()+"] 模拟 [任务一] 执行完成");
						
						Thread.sleep(1000L);
						latch.countDown();
						System.out.println("=======子线程 ["+Thread.currentThread().getName()+"] 模拟 [任务二] 执行完成");
						
						Thread.sleep(1000L);
						latch.countDown();
						System.out.println("=======子线程 ["+Thread.currentThread().getName()+"] 模拟 [任务三] 执行完成");
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
			
			latch.await();
			System.out.println("=======主线程 ["+Thread.currentThread().getName()+"] 等待子线程执行任务完成,resume 主线程====");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void foreach() {
		boolean condition = false;
		for(;condition;){
			System.out.println("condition loop");
		}

		for(;;){
			System.out.println("loop");
		}
	}
	
}
