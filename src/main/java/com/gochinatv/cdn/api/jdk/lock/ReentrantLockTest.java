package com.gochinatv.cdn.api.jdk.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

/**
 * Created by jacktomcat on 17/8/2.
 */
public class ReentrantLockTest {

    final java.util.concurrent.locks.Lock lock = new ReentrantLock();//锁对象
    final Condition notFull  = lock.newCondition();//写线程条件
    final Condition notEmpty = lock.newCondition();//读线程条件

    final Object[] items = new Object[1];//缓存队列
    int putptr/*写索引*/, takeptr/*读索引*/, count/*队列中存在的数据个数*/;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        System.out.println("执行put操作:::"+x);
        try {
            while (count == items.length) {//如果队列满了
                System.out.println("==========阻塞写");
                notFull.await();//阻塞写线程
            }
            items[putptr] = x;//赋值
            if (++putptr == items.length) putptr = 0;//如果写索引写到队列的最后一个位置了，那么置为0
            ++count;//个数++
            notEmpty.signal();//唤醒读线程
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {//如果队列为空
                System.out.println("==========阻塞读");
                Thread.sleep(10000);
                notEmpty.await();//阻塞读线程
            }
            Object x = items[takeptr];//取值
            if (++takeptr == items.length) takeptr = 0;//如果读索引读到队列的最后一个位置了，那么置为0
            --count;//个数--
            notFull.signal();//唤醒写线程
            return x;
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest test = new ReentrantLockTest();
        ExecutorService putService = Executors.newFixedThreadPool(10);
        for(int i=0;i<1;i++){
            Future<?> submit = putService.submit(() -> {
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
