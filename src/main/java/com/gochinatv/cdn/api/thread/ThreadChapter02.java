package com.gochinatv.cdn.api.thread;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//
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
			//chpater02.lock.readLock().lock();
			synchronized (chpater02.map) {
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("ReadThead=====" + chpater02.map.get("name"));
			}
			//chpater02.lock.readLock().unlock();
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
			//chpater02.lock.writeLock().lock();
			synchronized (chpater02.map) {
				Thread.sleep(2000L);
				chpater02.map.put("name", "lishi");
				Thread.sleep(5000L);
			}
			System.out.println("WriteThead=====synchronized=====end");
			//chpater02.lock.writeLock().unlock();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
