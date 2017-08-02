package com.gochinatv.cdn.api.jdk.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import org.junit.Test;


/**
 * 
 * http://ifeve.com/concurrency-semaphore/
 * 
 * @author jacktomcat
 *
 * Semaphore可以用于做流量控制，特别公用资源有限的应用场景，比如数据库连接。
 *   假如有一个需求，要读取几万个文件的数据，因为都是IO密集型任务，我们可以启动几十个线程并发的读取，但是如果读到内存后，还需要存储到数据库中，而数据库的连接数只有10个，
 *   这时我们必须控制只有十个线程同时获取数据库连接保存数据，否则会报错无法获取数据库连接。这个时候，我们就可以使用Semaphore来做流控
 * 
 */
public class SemaphoreTest {
	
	private static final int THREAD_COUNT = 300;

	private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
	
	private static Semaphore s = new Semaphore(10);


	/**
	 * 虽然有30个线程在执行，但是只允许10个并发的执行。Semaphore的构造方法Semaphore(int permits) 接受一个整型的数字，表示可用的许可证数量。
	 *  Semaphore(10)表示允许10个线程获取许可证，也就是最大并发数是10。Semaphore的用法也很简单，首先线程使用Semaphore的acquire()获取一个许可证，
	 *   使用完之后调用release()归还许可证。还可以用tryAcquire()方法尝试获取许可证。
	 */
	@Test
	public void semaphore() {
		for(int i=0;i<THREAD_COUNT;i++){
			threadPool.submit(new Runnable() {
				
				@Override
				public void run() {
					try {
						s.acquire();
						System.out.println(
								  "正在等待获取许可证的线程数:"+s.getQueueLength()+",当前可用的许可证数: "+s.availablePermits()+","
								+ "是否有线程正在等待获取许可证:"+s.hasQueuedThreads());
						s.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		threadPool.shutdown();
	}
}
