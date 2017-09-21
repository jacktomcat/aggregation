package com.gochinatv.cdn.api.jdk.thread;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调用另外一个同步方法,一个线程已经拥有某个线程的锁,再次申请的时候仍然会得到该对象的锁,
 * 也就是说synchronized获得的锁是可重入的
 *
 * Created by jacktomcat on 17/9/21.
 */
public class Synchronized01 {

    public synchronized void  m1(){

        System.out.println("m1 start");

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
    }

    synchronized void m2() {
        System.out.println("m2 start");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Synchronized01 sync = new Synchronized01();
        sync.m1();
    }

}
