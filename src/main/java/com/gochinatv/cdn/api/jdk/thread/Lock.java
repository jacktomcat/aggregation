package com.gochinatv.cdn.api.jdk.thread;

public class Lock{
	
    boolean isLocked = false;
    Thread  lockedBy = null;
    int lockedCount = 0;
    public synchronized void lock() throws InterruptedException{
        Thread thread = Thread.currentThread();
        while(isLocked && lockedBy != thread){
            wait();
            System.out.println("lock===");
        }
        isLocked = true;
        lockedCount++;
        lockedBy = thread;
    }
    
    public synchronized void unlock(){
        if(Thread.currentThread() == this.lockedBy){
            lockedCount--;
            if(lockedCount == 0){
                isLocked = false;
                notify();
                System.out.println("unlock===");
            }
        }
    }
    
    
    public synchronized void lock1() throws InterruptedException{
        while(isLocked){    
            wait();
        }
        System.out.println("lock1==");
        isLocked = true;
    }
    public synchronized void unlock1(){
        isLocked = false;
        notify();
        System.out.println("unlock1==");
    }
    
    
    public static void main(String[] args) {
		
    	Lock lock = new Lock();
    	try {
    		lock.lock1();
			lock.unlock1();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	}
}
