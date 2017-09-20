package com.gochinatv.cdn.api.jdk.thread;

/**
 * Created by jacktomcat on 17/9/20.
 * 测试线程关闭 方案三
 */
public class ThreadStop03 extends Thread {


    public void run() {
        try {
            sleep(50000);  // 延迟50秒
        } catch (InterruptedException e) {
            e.printStackTrace();//java.lang.InterruptedException: sleep interrupted
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) throws Exception {
        Thread thread = new ThreadStop03();
        thread.start();

        System.out.println("在50秒之内按任意键中断线程!");
        //System.in.read();

        thread.interrupt();
        System.out.println(thread.isInterrupted());//true
        thread.join();
        System.out.println("线程已经退出!");

    }

}
