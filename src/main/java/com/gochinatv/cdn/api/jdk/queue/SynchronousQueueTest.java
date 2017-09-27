package com.gochinatv.cdn.api.jdk.queue;

import org.junit.Test;

import java.util.concurrent.SynchronousQueue;

/**
 * Created by jacktomcat on 17/9/24.
 */
public class SynchronousQueueTest {

	@Test
	public void synchronousTest() {

		/**
		 * 同步队列,没有容量限制
		 * SynchronousQueue#add() 不能使用add()方法,只能使用put放入之后阻塞住,等待消费者消费
		 * 
		 * 适用于生产者-消费者场景中:如果生产一个,没有消费者去消费的话,那么就不生产了,同理,如果消费没有元素那么就阻塞住,等待生产
		 * 
		 */
		SynchronousQueue<String> queue = new SynchronousQueue<>();

		new Thread(() -> {
			try {
				queue.put("A");//如果没有消费,阻塞住
				queue.put("B");
				boolean isOffer = queue.offer("A");
				System.out.println("是否添加成功?:"+isOffer);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "生产者线程").start();

		try {
			Thread.sleep(5_000L);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		new Thread(() -> {
			while (true) {
				try {
					//System.out.println(queue.poll());//没有获取到返回null,否则返回获取的值
					//System.out.println(queue.peek());//永远返回null,查看源码
					//System.out.println(queue.element());//获取,但是不删除,如果没有此元素抛出  java.util.NoSuchElementException
					System.out.println(queue.take());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}, "消费者线程").start();

	}

}
