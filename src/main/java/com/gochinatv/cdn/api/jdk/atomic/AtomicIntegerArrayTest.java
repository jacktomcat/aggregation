package com.gochinatv.cdn.api.jdk.atomic;

import java.security.SecureRandom;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;

import org.junit.Test;

/**
 * 原子 AtomicIntegerArray
 * @author jacktomcat
 *
 */
public class AtomicIntegerArrayTest {


	ExecutorService service = Executors.newFixedThreadPool(10);
	Integer unsafe = new Integer(0);

	@Test
	public void test(){
		AtomicIntegerArray aia = new AtomicIntegerArray(10);
		aia.set(0,101);	//为数组第0号元素设置值为101

		aia.incrementAndGet(0);//为第0号元素 +1
		aia.getAndDecrement(0);//为第0号元素 -1

		int addAndGet = aia.addAndGet(0, 10);//为第0号元素 +10
		System.out.println("addAndGet:"+addAndGet);

		System.out.println(aia.length());//长度为10

		for(int i=0;i<aia.length();i++) {
			System.out.println(aia.get(i));//获取第 i 个元素
		}

		//测试多线程并发情况下面情况
		aia.set(0,0);//测试之前先把数组初始化到之前

		for(int i=0;i<10000;i++){

			service.submit(()-> {
				unsafe++;
				aia.incrementAndGet(0);
			});

		}
		service.shutdown();
		try {
			Thread.sleep(2000);
			service.awaitTermination(5, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		System.out.println("AtomicIntegerArray线程安全的值为:"+aia.get(0));//多次测试均是10000
		System.out.println("非线程安全的值为:"+unsafe);//多次测试均小于10000


		/**
		//获得数组第i个下标的元素
		public final int get(int i)


		//获得数组的长度
		public final int length()


		//将数组第i个下标设置为newValue，并返回旧的值
		public final int getAndSet(int i, int newValue)


		//进行CAS操作，如果第i个下标的元素等于expect，则设置为update，设置成功返回true
		public final boolean compareAndSet(int i, int expect, intupdate)


		//将第i个下标的元素加1
		public final int getAndIncrement(int i)


		//将第i个下标的元素减1
		public final int getAndDecrement(int i)


		//将第i个下标的元素增加delta（delta可以是负数）
		public final int getAndAdd(int i, int delta)*/

	}




}
