package com.gochinatv.cdn.api.jdk.concurrent;


/**
 * https://www.ibm.com/developerworks/cn/java/j-jtp06197.html
 * 
 * http://ifeve.com/%E4%BB%8Evolatile%E8%A7%A3%E8%AF%BBconcurrenthashmap%EF%BC%88jdk1-6-0%EF%BC%89%E6%97%A0%E9%94%81%E8%AF%BB/#more-20832
 *
 * Java 语言中的 volatile 变量可以被看作是一种 “程度较轻的 synchronized”；
 * 与 synchronized 块相比，volatile 变量所需的编码较少，并且运行时开销也较少，但是它所能实现的功能也仅是 synchronized 的一部分。
 *
 * @author jacktomcat
 *
 */
public class VolatileTest {

    /**
     * 性能考虑
     *
     * 使用 volatile 变量的主要原因是其简易性：在某些情形下，使用 volatile 变量要比使用相应的锁简单得多。
     * 使用 volatile 变量次要原因是其性能：某些情况下，volatile 变量同步机制的性能要优于锁。
     *
     *
     *  在目前大多数的处理器架构上，volatile 读操作开销非常低 —— 几乎和非 volatile 读操作一样。
     *  而 volatile 写操作的开销要比非 volatile 写操作多很多，因为要保证可见性需要实现内存界定（Memory Fence），
     *  即便如此，volatile 的总开销仍然要比锁获取低
     *
     *
     *  volatile 操作不会像锁一样造成阻塞，因此，在能够安全使用 volatile 的情况下，
     *   volatile 可以提供一些优于锁的可伸缩特性。如果读操作的次数要远远超过写操作，与锁相比，
     *   volatile 变量通常能够减少同步的性能开销。
     *
     */


    /**
     *
     * volatile常常用于修饰多线程共享变量，用来保证该变量的可见性。volatile的语意：某个写线程对volatile变量的写入马上可以被后续的某个读线程“看”到。
     *
     * volatile保证可见性的原理：volatile是通过在编译器生成字节码时，在对volatile变量进行读写指令序列的前后加入内存屏障，
     *  来禁止一些处理器重排序保证写入一定发生在读之前的这种happen-before关系。
     *
     *
     *
     *
     * 简单理解：在本次线程内，当读取一个变量时，为提高存取速度，编译器优化时有时会先把变量读取到一个线程本地内存中；
     *          以后再取变量值时，就直接从本地内存中取值；当变量值在本线程里改变时，会同时把变量的新值copy到本地内存中，以便保持一致；
     *          在某个特定的时候，将本地内存的更改写到系统主内存中去；当变量在因别的线程等而改变了值，并且该变化没有写到系统主内存，
     *          本次线程的本地内存中的值不会相应改变，从而造成应用程序读取的值和实际的变量值不一致；
     *
     *
     *          但是当变量被volatile修饰后，
     *          每次更改该变量的时候会将更改结果写到系统主内存中，利用多处理器的缓存一致性，其他处理器会发现自己的缓存行对应的内存地址被修改，
     *          就会将自己处理器的缓存行设置为失效，并强制从系统主内存获取最新的数据。
     *          这样就能保证即使在别的线程中改变了该变量的值，在本线程中也能取到最新更改后的值。
     *
     *       ConcurrentHashMap之所以有较好的并发性是因为ConcurrentHashMap是无锁读和加锁写，并且利用了分段锁（不是在所有的entry上加锁，而是在一部分entry上加锁）。
     *
     *
     */


}
