package com.gochinatv.cdn.api.jdk.atomic;


/**
 * http://ifeve.com/java-atomic/
 * http://blog.csdn.net/u013803262/article/details/72452932
 * @author jacktomcat
 *
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 一、何谓Atomic？
 *        Atomic一词跟原子有点关系，后者曾被人认为是最小物质的单位。计算机中的Atomic是指不能分割成若干部分的意思。
 *     如果一段代码被认为是Atomic，则表示这段代码在执行过程中，是不能被中断的。
 *
 *     通常来说，原子指令由硬件提供，供软件来实现原子方法（某个线程进入该方法后，就不会被中断，直到其执行完成）
 *
 * 二、自旋锁定义:
 *
 *     谓自旋锁？它是为实现保护共享资源而提出一种锁机制。其实，自旋锁与互斥锁比较类似，它们都是为了解决对某项资源的互斥使用。
 *     无论是互斥锁，还是自旋锁，在任何时刻，最多只能有一个保持者，也就说，在任何时刻最多只能有一个执行单元获得锁。
 *     但是两者在调度机制上略有不同。对于互斥锁，如果资源已经被占用，资源申请者只能进入睡眠状态。
 *
 *     但是自旋锁不会引起调用者睡眠，如果自旋锁已经被别的执行单元保持，调用者就一直循环在那里看是否该自旋锁的保持者已经释放了锁，
 *     "自旋"一词就是因此而得名。
 *
 */
public class AtomicIntegerTest {

    /**
     * AtomicInteger这个类的存在是为了满足在高并发的情况下,原生的整形数值自增线程不安全的问题。比如说
     *
     *   int i = 0 ;
     *   i++;
     *
     *   上面的写法是线程不安全的。
     *   有的人可能会说了,可以使用synchronized关键字啊。
     *   但是这里笔者要说的是,使用了synchronized去做同步的话系统的性能将会大大下降。
     *   所以此时AtomicInteger这个类的使用就可以满足上述的情况。
     *   当我们统计一个页面的浏览量的时候,可以使用该类来统计,而不再使用++运算符。
     */

    public static final AtomicInteger atomicInteger = new AtomicInteger(0);
    private static int threadCount = 2000;


    //Exception in thread "main" java.lang.OutOfMeoryError: unable to create new native thread
    private  static void atomicIntegerTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {

            //下面这个例子里的λ表达式没有参数，也没有返回值（
            // 相当于一个方法接受0个参数，返回void，其实就是Runnable里run方法的一个实现）：
            executorService.execute(() -> {
                for (int j = 0; j < 4; j++) {
                    System.out.println(atomicInteger.getAndIncrement());
                }
            });
        }

        executorService.shutdown();
    }


    public static void main(String[] args) throws InterruptedException {
        atomicIntegerTest();
        Thread.sleep(3000);
        System.out.println("使用AtomicInteger最终结果是" + atomicInteger.get());

        incrementIntegerTest();
        System.out.println("使用普通的i++ 最终结果是 " + value);

        /**
         * 结果:这里设置2000个线程并发,导致的结果,使用AtomicInteger和使用传统的方式比较:
         *  这里多试几次就可以看出区别:
         *
         *  1:  使用AtomicInteger最终结果是8000
         *  2:  使用普通的i++ 最终结果是 7996
         *
         */
    }



    public static int value = 0;

    private  static void incrementIntegerTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {

            //下面这个例子里的λ表达式没有参数，也没有返回值（
            // 相当于一个方法接受0个参数，返回void，其实就是Runnable里run方法的一个实现）：
            executorService.execute(() -> {
                for (int j = 0; j < 4; j++) {
                    value++;
                    //System.out.println(value++);
                }
            });
        }

        executorService.shutdown();
    }

}
