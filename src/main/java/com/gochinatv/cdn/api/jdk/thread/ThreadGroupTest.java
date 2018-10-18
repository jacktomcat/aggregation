package com.gochinatv.cdn.api.jdk.thread;


/**
 * 测试线程组
 */
public class ThreadGroupTest {


    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println(thread.toString());          // Thread[Thread-0,5,main]    线程名称,线程优先级,线程组

        ThreadGroup group = new ThreadGroup("dc-alarm-app"); // 手动设置group的名称
        thread = new Thread(group,"thread-group-01");        // 手动设置线程名称
        System.out.println(thread.toString());          // Thread[Thread-0,5,main]    线程名称,线程优先级,线程组


        System.out.println(Thread.currentThread());     // Thread[main,5,main]

    }

}
