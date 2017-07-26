package com.gochinatv.cdn.api.jdk.thread;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * readLock和synchronized 区别
 * 1、 ReadWriteLock有一个实现类：ReentrantReadWriteLock，它实现了Lock里面的方法，但是使用Lock的时候必须注意它不会像synchronized执行完成之后或者抛出异常之后自动释放锁，而是需要你主动释放锁，
 * 所以我们必须在使用Lock的时候加上try{}catch{}finally{}块，并且在finally中释放占用的锁资源。
 * 
 * 2、最大的区别就是当使用synchronized，一个线程抢占到锁资源，其他线程必须像SB一样得等待； 
 * ReadWriteLock 当读取的时候线程会获得read锁，其他线程也可以获得read锁同时并发的去读取，但是写程序运行获取到write锁的时候，其他线程是不能进行操作的，因为write是排它锁
 * @author zhuhh
 *
 */
public class ThreadChapter02 {

	ReadWriteLock lock = new ReentrantReadWriteLock();
	HashMap<String, String> map = new HashMap<>();

	public static void main(String[] args) {
		ThreadChapter02 chpater02 = new ThreadChapter02();
		
		Thread write = new WriteThead(chpater02);
		write.start();
		
		Thread read = new ReadThead(chpater02);
		read.start();
	}
	
}

class ReadThead extends Thread {
	ThreadChapter02 chpater02;

	public ReadThead(ThreadChapter02 chpater02) {
		this.chpater02 = chpater02;
	}

	@Override
	public void run() {
		while (true) {
			chpater02.lock.readLock().lock();
			// synchronized (chpater02.map) {
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("ReadThead=====" + chpater02.map.get("name"));
			// }
			chpater02.lock.readLock().unlock();
		}
	}
	
}

class WriteThead extends Thread {
	ThreadChapter02 chpater02;

	public WriteThead(ThreadChapter02 chpater02) {
		this.chpater02 = chpater02;
	}

	@Override
	public void run() {
		try {
			chpater02.lock.readLock().lock();
			// chpater02.lock.writeLock().lock();
			// synchronized (chpater02.map) {
			Thread.sleep(2000L);
			chpater02.map.put("name", "lishi");
			Thread.sleep(5000L);
			// }
			System.out.println("WriteThead=====synchronized=====end");
			// chpater02.lock.writeLock().unlock();
			chpater02.lock.readLock().unlock();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
