package com.gochinatv.cdn.api.jdk.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * 
 * @author jacktomcat
 *
 */
public class DelayQueueTest {
   
	/**
	 * 此队列可以做定时任务
	 * 定时执行,如果延迟小于等于0,才会执行,否则一直处于阻塞状态
	 */
	@Test
	public void delayQueueTest() {
		DelayQueue<DelaydTest> queue = new DelayQueue<>();
		queue.add(new DelaydTest(5000, 200,"queue01"));//延迟5000毫秒
		queue.add(new DelaydTest(100, 4,"queue02"));//延迟100毫秒
		queue.add(new DelaydTest(100, 3,"queue03"));//延迟100毫秒
		queue.add(new DelaydTest(100, 100,"queue04"));//延迟100毫秒
		queue.add(new DelaydTest(7000, 8,"queue05"));//延迟100毫秒
		
		System.out.println(queue.size());
		try {
			DelaydTest take;
			while(null!=( take = queue.take())){
				System.out.println(take);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}


class DelaydTest implements Delayed{

	private int id;
	private String name;
	private long delay;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public DelaydTest(long delay, int id, String name) {
		super();
		this.delay = delay + System.currentTimeMillis();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "DelaydTest [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int compareTo(Delayed o) {
		//System.out.println("this=="+this.toString()+",o=="+o.toString());
		DelaydTest other = (DelaydTest)o;
		if(this==other){
			return 0;
		}else if(this.getId()>other.getId()){
			return 1;
		}else if(this.getId()==other.getId()){
			return 0;
		}
		return -1;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		long convert = unit.convert(delay-System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		return convert;
	}
	
}
