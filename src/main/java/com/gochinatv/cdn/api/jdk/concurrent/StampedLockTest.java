package com.gochinatv.cdn.api.jdk.concurrent;

import java.util.concurrent.locks.StampedLock;

import org.junit.Test;

/**
 * http://blog.csdn.net/sunfeizhi/article/details/52135136
 * @author jacktomcat
 *
 */
public class StampedLockTest {

    /**
     * Stamped类似一个时间戳的作用，每次写的时候对其+1来改变被操作对象的Stamped值
     * 这样其它线程读的时候发现目标对象的Stamped改变，则执行重读
     */
	StampedLock stampedLock = new StampedLock();
	
	@Test
    public void stampedLock() {
		
		
    }
	
}
