package com.gochinatv.cdn.api.thread;

public class ThreadChapter01 {
   
	String name = "";
	public static void main(String[] args) {
		ThreadChapter01 chpater01 = new ThreadChapter01();
		Thread thread = new Car(chpater01);
		thread.start();
		chpater01.getName();
	}
	
	public void getName(){
		try {
			synchronized(name){
				name = "zhangsan";
				Thread.sleep(5000L);
				System.out.println("=====synchronized====="+name);
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
			Thread.sleep(2000L);
			chpater01.name="lisi";
			System.out.println(chpater01.name);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
