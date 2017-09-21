package com.gochinatv.cdn.api.jdk.queue;


import org.junit.Test;

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
public class BlockingQueueTest {


    @Test
    public void linkedBlockQueueTest() {

        //无界队列,最大值是  Integer.MAX_VALUE
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(1);
        boolean add = queue.add("queue01");
        System.out.println("add:"+add + ",size:"+queue.size());

        try {
            queue.put("queue02");
            System.out.println("size:"+queue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean offer = queue.offer("queue03");
        System.out.println("offer:"+offer + ",size:"+queue.size());

        String poll = queue.poll();
        System.out.println("poll:"+poll + ",size:"+queue.size());

        String peek = queue.peek();
        System.out.println("peek:"+peek + ",size:"+queue.size());

        String element = queue.element();
        System.out.println("element:"+element + ",size:"+queue.size());

        boolean contains = queue.contains("queue03");
        System.out.println("contains:"+contains + ",size:"+queue.size());

        try {
            String pollTimeOut = queue.poll(5, TimeUnit.SECONDS);
            System.out.println("pollTimeOut:"+pollTimeOut + ",size:"+queue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            boolean queueOffer2 = queue.offer("queueOffer2", 5, TimeUnit.SECONDS);
            System.out.println("queueOffer2:"+queueOffer2 + ",size:"+queue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
