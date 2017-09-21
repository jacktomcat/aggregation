package com.gochinatv.cdn.api.jdk.thread;

/**
 *
 * 程序在执行的过程中,如果出现异常,默认情况锁会被释放,
 * 所以在并发处理过程中,有异常要多加小心,不然可能发生数据不一致的情况,
 * 在多线程的过程中,如果第一个线程发生异常抛出异常,其它的线程就会进入同步代码块,可能会产生脏数据
 * Created by jacktomcat on 17/9/21.
 */
public class Synchronized03 {

    int count = 0;
    public synchronized  void m1(){
        while (true){
            count++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(count==5){
                //此处异常,锁将被释放,要想不释放锁,可以在这里进行异常处理,然后让循环继续
                int i =count/0;
                System.out.println("此处有异常:"+i);
            }
        }

    }

    public static void main(String[] args) {
        new Synchronized03().m1();
    }

}
