package com.gochinatv.cdn.api.jdk.queue;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Test;


/**
 * 
 * http://blog.csdn.net/yjian2008/article/details/16951811
 * @author jacktomcat
 *
 */
public class LinkedTransferQueueTest {

	@Test
	public void linkedTransferQueueTest() {
		LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
		queue.add("add01");
		
		new Thread(()->{
			try {
				String take;
				while(true){
					take = queue.take();
					if(null!=take)
					System.out.println("取出元素:"+take);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
		
		//若当前存在一个正在等待获取的消费者线程（使用take()或者poll()函数），使用该方法会即刻转移/传输对象元素e；若不存在，则返回false，并且不进入队列。这是一个不阻塞的操作
		queue.tryTransfer("tryTransfer");
		
		
		//若当前存在一个正在等待获取的消费者线程，会立即传输给它;否则将插入元素e到队列尾部，并且等待被消费者线程获取消费掉；若在指定的时间内元素e无法被消费者线程获取，则返回false，同时该元素被移除。
		try {
			queue.tryTransfer("transfer02", 10, TimeUnit.SECONDS);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		 
		try {
			//若当前存在一个正在等待获取的消费者线程，即立刻移交之；否则，会插入当前元素e到队列尾部，并且等待进入阻塞状态，到有消费者线程取走该元素。
			queue.transfer("transfer03");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//获取所有等待获取元素的消费线程数量。
		int waitingConsumerCount = queue.getWaitingConsumerCount();
		System.out.println("waitingConsumerCount:"+waitingConsumerCount);
		
		//判断是否存在消费者线程。
		boolean hasWaitingConsumer = queue.hasWaitingConsumer();
		System.out.println("hasWaitingConsumer:"+hasWaitingConsumer);
		
	}
	
}
