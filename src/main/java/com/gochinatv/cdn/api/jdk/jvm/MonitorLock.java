package com.gochinatv.cdn.api.jdk.jvm;

/**
 * Created by jacktomcat on 17/9/24.
 * 1: 使用jconsole监控查看线程的状态,线程1获得锁,其它的线程等待锁
 * 2: jstack pid 进行监控
 */
public class MonitorLock {


    public static void main(String[] args) {

        MonitorLock lock = new MonitorLock();
        for (int i = 0; i < 5; i++) {
            MonitorTestLock testLock = new MonitorTestLock(lock);
            //Thread thread = new Thread(testLock,MonitorLock.class.getName()+"-"+i+"-");
            Thread thread = new Thread(testLock,"线程:"+(i+1));
            thread.start();
        }
        
    }

    public synchronized void monitor(){
        try {
            System.out.println(Thread.currentThread().getName()+"获得锁");
            Thread.sleep(3*60*1000L);
            System.out.println(Thread.currentThread().getName()+"释放锁");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


class MonitorTestLock implements Runnable{

    private MonitorLock lock;
    public MonitorTestLock(MonitorLock lock){
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.monitor();
    }
}
