package com.gochinatv.cdn.api.jdk.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Test;


/**
 * 原子boolean
 *
 * @author jacktomcat
 */
public class AtomicBooleanTest {

    @Test
    public void test() {
        AtomicBoolean ab = new AtomicBoolean(true);
        System.out.println(ab.get());//true

        ab.set(false);
        System.out.println(ab.get());//false

        boolean andSet = ab.getAndSet(true);
        System.out.println(andSet);//false
        System.out.println(ab.get());//true


        //而lazySet()的用法，就是在不需要让共享变量的修改立刻让其他线程可见的时候，以设置普通变量的方式来修改共享状态，
        // 可以减少不必要的内存屏障，从而提高程序执行的效率。
        ab.lazySet(false);
        System.out.println(ab.get());

        ab.compareAndSet(false, true);//如果期望值和当前值相等,那么修改值,否则不进行修改
        System.out.println(ab.get());
    }
}
