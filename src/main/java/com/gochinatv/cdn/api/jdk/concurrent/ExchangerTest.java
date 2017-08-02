package com.gochinatv.cdn.api.jdk.concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

/**
 *
 * http://ifeve.com/concurrency-exchanger/
 *
 * @author jacktomcat
 *
 * Exchanger（交换者）是一个用于线程间协作的工具类。Exchanger用于进行线程间的数据交换。
 * 它提供一个同步点，在这个同步点两个线程可以交换彼此的数据。这两个线程通过exchange方法交换数据， 如果第一个线程先执行exchange方法，它会一直等待第二个线程也执行exchange，
 * 当两个线程都到达同步点时，这两个线程就可以交换数据，将本线程生产出来的数据传递给对方。
 *
 *
 */
public class ExchangerTest {

	ExecutorService threadPool = Executors.newFixedThreadPool(2);
	Exchanger<String> ex = new Exchanger<>();


	/**
	 *
	 * 如果两个线程有一个没有到达exchange方法，则会一直等待,如果担心有特殊情况发生，
	 * 避免一直等待，
	 * 可以使用exchange(V x, long timeout, TimeUnit unit)设置最大等待时长。
	 *
	 */
	@Test
	public void exchanger() {
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					String A = "银行流水A";// A录入银行流水数据
					String B = ex.exchange(A);

					System.out.println(
							"线程1:  A和B数据是否一致：" + A.equals(B) + ",A录入的是："
									+ A + ",B录入是：" + B);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});


		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					String B = "银行流水B";// A录入银行流水数据
					String A = ex.exchange(B);

					System.out.println(
							"线程2:  A和B数据是否一致：" + A.equals(B) + ",A录入的是："
							+ A + ",B录入是：" + B);


				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		threadPool.shutdown();

	}
	
}
