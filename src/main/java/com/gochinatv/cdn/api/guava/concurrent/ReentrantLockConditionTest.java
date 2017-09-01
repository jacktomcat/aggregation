package com.gochinatv.cdn.api.guava.concurrent;

import java.util.concurrent.locks.ReentrantLock;



public class ReentrantLockConditionTest<V> {

	
	private final ReentrantLock lock = new ReentrantLock();
    private final Condition valuePresent = lock.newCondition();//read condition
    private final Condition valueAbsent = lock.newCondition();//write condition
    private V value;

    public V get() throws InterruptedException {
        lock.lock();
        try {
            while (value == null) {
                //读线程等待
                valuePresent.await();
            }
            V result = value;
            value = null;
            //value置为null的时候,指定唤醒write condition.
            valueAbsent.signal();
            return result;
        } finally {
            lock.unlock();
        }
    }
    public void set(V newValue) throws InterruptedException {
        lock.lock();
        try {
            while (value != null) {
                //value还存在,不可以写,写线程等待
                valueAbsent.await();
            }
            value = newValue;
            //指定唤醒read线程,表示可读
            valuePresent.signal();
        } finally {
            lock.unlock();
        }
    }
    
}
