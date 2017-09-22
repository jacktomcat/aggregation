package com.gochinatv.cdn.api.jdk.queue;


import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;


/**
 * http://286.iteye.com/blog/2297295
 *
 * http://www.infoq.com/cn/articles/java-blocking-queue
 * 
 * @author jacktomcat
 *
 */
public class BlockingQueueTest03 {


    @Test
    public void arrayBlockQueueTest() {

        Queue<String> queue = new ConcurrentLinkedQueue<>();

        queue.add("aaa");
        queue.add("bbb");



    }


}
