package com.gochinatv.cdn.api.jdk.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhuhuihui on 17/9/3.
 */
public class SynchronizedTest {



    public synchronized void put(Object x) throws InterruptedException {
        System.out.println("执行put操作:::"+x);
        System.out.println("============put======");
    }

    public synchronized  Object take() throws InterruptedException {
            System.out.println("==========get");
        Thread.sleep(10000L);
            return "aaaa";
    }


    public static void main(String[] args) throws InterruptedException {
        SynchronizedTest test = new SynchronizedTest();
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
