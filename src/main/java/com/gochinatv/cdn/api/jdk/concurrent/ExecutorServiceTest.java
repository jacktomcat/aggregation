package com.gochinatv.cdn.api.jdk.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;


/**
 * 
 * @author jacktomcat
 *
 */
public class ExecutorServiceTest {

	RejectedExecutionHandler defaultHandler = new ThreadPoolExecutor.AbortPolicy();
	ExecutorService threadPool = new ThreadPoolExecutor(10,10,0,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(),new SetThreadNameFactory("wrap"),defaultHandler);


	AtomicInteger atomic = new AtomicInteger(0);
	int counter = 0;

	/**
	 * 这里自定义一个线程工厂的实现，可用自定义线程的名字，这样在以后排查问题的时候方便排查
	 */
	static class SetThreadNameFactory implements ThreadFactory {
		private static final AtomicInteger poolNumber = new AtomicInteger(1);
		private final ThreadGroup group;
		private final AtomicInteger threadNumber = new AtomicInteger(1);
		private final String namePrefix;

		SetThreadNameFactory(String prefix) {
			SecurityManager s = System.getSecurityManager();
			group = (s != null) ? s.getThreadGroup() :
					Thread.currentThread().getThreadGroup();
			namePrefix = prefix+"-" + poolNumber.getAndIncrement() + "-";
		}

		public Thread newThread(Runnable r) {
			Thread t = new Thread(group, r,
					namePrefix + threadNumber.getAndIncrement(),
					0);
			if (t.isDaemon())
				t.setDaemon(false);
			if (t.getPriority() != Thread.NORM_PRIORITY)
				t.setPriority(Thread.NORM_PRIORITY);
			return t;
		}
	}

	
	@Test
	public void testExecute(){



		CountDownLatch countDownLatch = new CountDownLatch(100);
		StopWatch watch  = StopWatch.createStarted();
		for (int i = 0; i < 100; i++) {
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					int incrementAndGet = atomic.incrementAndGet();
					System.out.println(Thread.currentThread().getName() + "线程安全, 当前任务第 " + incrementAndGet + " 个");
					try {
						TimeUnit.SECONDS.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					countDownLatch.countDown();
				}
			});


			/*threadPool.execute(() -> {
				try {
					int incrementAndGet = atomic.incrementAndGet();
					System.out.println(Thread.currentThread().getName() + "线程安全, 当前任务第 " + incrementAndGet + " 个");
					TimeUnit.SECONDS.sleep(5);
					countDownLatch.countDown();
					//counter++;
					//System.out.println("线程非安全, 当前任务第 " + counter + " 个");
				} catch (Exception e) {
					e.printStackTrace();
				}
			});*/
		}
		System.out.println("准备执行await");
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("执行完毕耗时"+watch.getTime(TimeUnit.SECONDS));

		//threadPool.shutdown();
		/*try {
			threadPool.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
	}

	
	@Test
	public void testSubmit() throws Exception{
		for (int i = 0; i < 100; i++) {
			Future<?> future = threadPool.submit(new Runnable() {
				@Override
				public void run() {
					int incrementAndGet = atomic.incrementAndGet();
					System.out.println("线程安全, 当前任务第 " + incrementAndGet + " 个");
				}
			});
			Object object = future.get();
			System.out.println(object+"::");//返回null
		}
		threadPool.shutdown();
		try {
			threadPool.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void testSubmit1() throws Exception{
		for (int i = 0; i < 100; i++) {
			String result=""+i;
			Future<?> future = threadPool.submit(new Runnable() {
				@Override
				public void run() {
					int incrementAndGet = atomic.incrementAndGet();
					System.out.println("线程安全, 当前任务第 " + incrementAndGet + " 个");
				}
			},result);//参数两个参数的返回结果,需要自己定义
			Object object = future.get();
			System.out.println(object+"::");//返回i
		}
		threadPool.shutdown();
		try {
			threadPool.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void testSubmitCallable() throws Exception{
		for (int i = 0; i < 100; i++) {
			Future<String> future = threadPool.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					int incrementAndGet = atomic.incrementAndGet();
					return incrementAndGet+"批次";
				}
			});
			
			Object object = future.get();
			System.out.println(object+"::");//返回i
		}
		threadPool.shutdown();
		try {
			threadPool.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
