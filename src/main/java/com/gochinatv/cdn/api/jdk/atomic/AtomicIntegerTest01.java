package com.gochinatv.cdn.api.jdk.atomic;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * http://ifeve.com/java-atomic/
 * http://blog.csdn.net/u013803262/article/details/72452932
 * @author jacktomcat
 *
 */

public class AtomicIntegerTest01 {



    public static void main(String[] args) throws InterruptedException {

        AtomicInteger atomicInt = new AtomicInteger(10);
        int get = atomicInt.get();
        System.out.println("初始值 get: "+get);

        atomicInt.set(15);
        int get1 = atomicInt.get();
        System.out.println("set(15) 之后 get: "+get1);

        int andSet = atomicInt.getAndSet(12);
        int get2 = atomicInt.get();
        System.out.println("getAndSet(12): 老值: " + andSet + " 新值: "+get2);

        /*
        atomicInt.accumulateAndGet()
        atomicInt.addAndGet()
        atomicInt.compareAndSet()
        atomicInt.decrementAndGet()

        atomicInt.getAndAccumulate()
        atomicInt.getAndAdd()
        atomicInt.getAndDecrement();
        atomicInt.getAndIncrement();

        atomicInt.incrementAndGet();

        atomicInt.updateAndGet()
        atomicInt.weakCompareAndSet();
        */

    }



}
