package com.gochinatv.cdn.api.jdk.thread;

import java.util.concurrent.TimeUnit;

/**
 * 这里是继承中可能发生的情况,子类调用父类的同步方法
 * Created by jacktomcat on 17/9/21.
 */
public class Synchronized02 {


    public synchronized void  m1(){

        System.out.println("m1 start");
        //这里输出的是com.gochinatv.cdn.api.jdk.thread.subClass
        //所以这里的锁的对象是  子类的,因为子类继承的是父类
        System.out.println(this.getClass().getName());
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m1 end");
    }


    public static void main(String[] args) {
        new subClass().m1();

    }
}


class subClass extends  Synchronized02{

    public synchronized void  m1(){

        System.out.println("sub m1 start");

        super.m1();

        System.out.println("sub m1 end");
    }
}
