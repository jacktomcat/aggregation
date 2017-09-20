package com.gochinatv.cdn.api.jdk.thread;

/**
 * Created by jacktomcat on 17/9/20.
 * 测试线程关闭 方案二
 */
public class ThreadStop02 extends Thread{


    public void run()
    {
        while (true){
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("处理业务");
        }
    }


    public static void main(String[] args) throws Exception
    {
        ThreadStop02 thread = new ThreadStop02();
        thread.start();
        sleep(5000); // 主线程延迟5秒
        thread.stop();

        //等待thread线程结束加入到主线程中串行化,如果不加入join这句代码,
        // 那么可能导致主线程已经执行完成,但是thread线程还在行
        thread.join();

        System.out.println("线程退出!");
    }

}
