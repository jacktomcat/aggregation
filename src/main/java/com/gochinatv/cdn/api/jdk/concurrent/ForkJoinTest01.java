package com.gochinatv.cdn.api.jdk.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 *
 * https://www.ibm.com/developerworks/cn/java/j-lo-forkjoin/
 *
 * http://blog.csdn.net/kanglix1an/article/details/46475419
 *
 * http://blog.csdn.net/xuguoli_beyondboy/article/details/44288047
 *
 * Created by jacktomcat on 17/8/3.
 */

/**
 *
 *     没有返回值得并行计算
 *
 *     继承RecursiveAction来实现"可分解"的任务
 */
public class ForkJoinTest01 extends RecursiveAction {


    // 每个“小任务”只最多只打印50个数
    private static final int THRESHOLD = 10;
    private int start;
    private int end;


    // 打印从start到end的任务
    public ForkJoinTest01(int start, int end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected void compute() {
        // 当end与start之间的差小于THRESHOLD时，开始打印
        if(end - start < THRESHOLD) {
            for (int i = start ; i < end ; i++ ) {
                System.out.println(Thread.currentThread().getName() + "的i值：" + i);
            }
        } else {
            // 如果当end与start之间的差大于THRESHOLD时，即要打印的数超过50个
            // 将大任务分解成两个小任务。
            int middle = (start + end) /2;
            ForkJoinTest01 left = new ForkJoinTest01(start, middle);
            ForkJoinTest01 right = new ForkJoinTest01(middle, end);
            // 并行执行两个“小任务”
            left.fork();
            right.fork();
        }
    }


    public static void main(String[] args)throws Exception {
        ForkJoinPool pool = new ForkJoinPool();
        // 提交可分解的PrintTask任务
        pool.submit(new ForkJoinTest01(0 , 50));
        //线程阻塞，等待所有任务完成
        pool.awaitTermination(2, TimeUnit.SECONDS);
        // 关闭线程池
        pool.shutdown();
    }

}