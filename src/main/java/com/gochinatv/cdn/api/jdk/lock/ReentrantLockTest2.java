package com.gochinatv.cdn.api.jdk.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jacktomcat on 17/8/2.
 */
public class ReentrantLockTest2 {

    final Lock lock = new ReentrantLock();//锁对象


    public void put(Object x) throws InterruptedException {
        lock.lock();
        System.out.println("执行put操作:::"+x);
        try {
            System.out.println("============put======");
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            Thread.sleep(10000L);
            System.out.println("==========get");
            return "aaaa";
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest2 test = new ReentrantLockTest2();
        ExecutorService putService = Executors.newFixedThreadPool(10);
        for(int i=0;i<1;i++){
            putService.submit(() -> {
                try {
                    test.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            putService.submit(()->{
                System.out.println("=======立即执行put");
                String data = "data";
                try {
                    test.put(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }

        putService.shutdown();
        putService.awaitTermination(1, TimeUnit.MINUTES);

    }

}
