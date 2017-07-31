package com.gochinatv.cdn.api.jdk.concurrent;


/**
 * http://www.cnblogs.com/mickole/articles/3757278.html
 * java不能直接访问操作系统底层，而是通过本地方法来访问。Unsafe类提供了硬件级别的原子操作，主要提供了以下功能：
 * 1、通过Unsafe类可以分配内存，可以释放内存；
 *   类中提供的3个本地方法allocateMemory、reallocateMemory、freeMemory分别用于分配内存，扩充内存和释放内存，与C语言中的3个方法对应。
 *   
 * 2、可以定位对象某字段的内存位置，也可以修改对象的字段值，即使它是私有的；
 * 
 * 3、挂起与恢复
 * 	   将一个线程进行挂起是通过park方法实现的，调用 park后，线程将一直阻塞直到超时或者中断等条件出现。unpark可以终止一个挂起的线程，使其恢复正常。
 *   整个并发框架中对线程的挂起操作被封装在 LockSupport类中，LockSupport类中有各种版本pack方法，但最终都调用了Unsafe.park()方法。
 *   
 * 4、CAS操作  (Compare And Swap 比较和交换)
 * 	   是通过compareAndSwapXXX方法实现的
 * 
 * @author jacktomcat
 *
 */
public class UnsafeTest {

}
