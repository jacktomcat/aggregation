package com.gochinatv.cdn.api.jdk.atomic;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntBinaryOperator;

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

        //incrementAndGet
        int decrementAndGet = atomicInt.decrementAndGet();
        System.out.println("decrementAndGet: " + decrementAndGet );
        
        //getAndIncrement(),getAndDecrement(),getAndAdd()
        int addAndGet = atomicInt.addAndGet(10);
        System.out.println("addAndGet(10): " + addAndGet );
        
        //由于原值与期望值不一致,所以这里返回false,更新也不会成功
        boolean compareAndSet = atomicInt.compareAndSet(99, 100);
        System.out.println("compareAndSet 原值为21, 期望值是99,更新值为100,是否是一致: " + compareAndSet );
        System.out.println("比较和set之后的值为:"+atomicInt.get());
        
        //由于原值与期望值一致,所以这里返回true,更新成功为100
        boolean compareAndSet1 = atomicInt.compareAndSet(21, 100);
        System.out.println("compareAndSet 原值为21, 期望值是21,更新值为100,是否是一致: " + compareAndSet1 );
        System.out.println("比较和set之后的值为:"+atomicInt.get());
        
        atomicInt.getAndAccumulate(30, new IntBinaryOperator() {
			@Override
			public int applyAsInt(int left, int right) {
				System.out.println(left+"======="+right);
				return right;
			}
		});
        System.out.println("getAndAccumulate之后:"+ atomicInt.get());
        
        
        
        /*atomicInt.accumulateAndGet()
        atomicInt.getAndAccumulate()
        atomicInt.updateAndGet()
        atomicInt.weakCompareAndSet();
        */

    }



}
