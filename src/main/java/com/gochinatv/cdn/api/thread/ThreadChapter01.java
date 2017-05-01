package com.gochinatv.cdn.api.thread;

import java.util.HashMap;

//一个主线程，一个car线程
public class ThreadChapter01 {
   
	HashMap<String,String> map = new HashMap<>();
	
	public static void main(String[] args) {
		ThreadChapter01 chpater01 = new ThreadChapter01();
		Thread thread = new Car(chpater01);
		thread.start();
		chpater01.setMap();
	}
	
	public void setMap(){
		try {
			System.out.println("=============start main thread");
			synchronized(map){
				Thread.sleep(5000L);
				map.put("name", "zhangsan");
				System.out.println("main=====synchronized====="+map.get("name"));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


class Car extends Thread{
	ThreadChapter01 chpater01;
	
    public Car(ThreadChapter01 chpater01) {
		this.chpater01 = chpater01;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("=============start car thread");
			synchronized (chpater01.map) {
				Thread.sleep(2000L);
				chpater01.map.put("name", "lishi");
			}
			System.out.println("car=====synchronized====="+chpater01.map.get("name"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
