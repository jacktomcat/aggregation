package com.gochinatv.cdn.api.jdk.queue;


import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


/**
 * http://286.iteye.com/blog/2297295
 *
 * http://www.infoq.com/cn/articles/java-blocking-queue
 * 
 * @author jacktomcat
 *
 */
public class BlockingQueueTest02 {


    @Test
    public void arrayBlockQueueTest() {

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

        queue.add("aaa");
        queue.add("bbb");

        //添加元素到队列,有可用空间添加并返回true,否则抛出异常
        boolean add = queue.add("queue01");
        System.out.println("add:"+add + ",size:"+queue.size());

        try {
            queue.put("queue02");//阻塞住,有可用空间才会向里面放
            System.out.println("size:"+queue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean offer = queue.offer("queue03");//如果有可用空间返回插入并返回true,否则返回false
        System.out.println("offer:"+offer + ",size:"+queue.size());

        try {
            //如果有可用空间返回插入并返回true,否则返回false
            boolean queueOffer2 = queue.offer("queueOffer2", 5, TimeUnit.SECONDS);
            System.out.println("queueOffer2:"+queueOffer2 + ",size:"+queue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        queue.clear();

        String poll = queue.poll();//获取并移除头元素,如果没有返回null
        System.out.println("poll:"+poll + ",size:"+queue.size());

        try {
            //检索并删除此队列的头，如果需要的元素可用，则等待到指定的等待时间。
            String pollTimeOut = queue.poll(5, TimeUnit.SECONDS);
            System.out.println("pollTimeOut:"+pollTimeOut + ",size:"+queue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String take = queue.take();
        System.out.println("take:"+take + ",size:"+queue.size());

        //获取,但是不移除头部元素
        String peek = queue.peek();
        System.out.println("peek:"+peek + ",size:"+queue.size());

        //Retrieves, but does not remove, the head of this queue.
        // This method differs from peek only in that it throws an exception if this queue is empty.
        String element = queue.element();
        System.out.println("element:"+element + ",size:"+queue.size());

        boolean contains = queue.contains("queue03");
        System.out.println("contains:"+contains + ",size:"+queue.size());





    }


}
