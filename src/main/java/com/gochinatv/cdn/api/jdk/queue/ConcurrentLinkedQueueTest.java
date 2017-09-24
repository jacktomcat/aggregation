package com.gochinatv.cdn.api.jdk.queue;

import org.junit.Test;

import java.util.AbstractQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jacktomcat on 17/9/24.
 */
public class ConcurrentLinkedQueueTest {


    @Test
    public void concurrentLinkQueueTest() {

        AbstractQueue<String> queue = new ConcurrentLinkedQueue<>();

        //添加元素到队列,有可用空间添加并返回true,否则抛出异常
        //但是本身ConcurrentLinkedQueue是无界队列
        boolean add = queue.add("queue01");

        System.out.println("add:"+add + ",size:"+queue.size());

        //queue.add(null); //添加null元素抛出空指针异常


        boolean add2 = queue.add("queue02");
        System.out.println("add2:"+add2 + ",size:"+queue.size());

        //添加元素到队列,添加成功返回true,否则false
        boolean offer = queue.offer("queue03");
        System.out.println("offer:"+offer + ",size:"+queue.size());

        //queue.clear();

        //获取元素
        String element = queue.element();//获取元素,但是不删除,如果没有可用元素,抛出异常
        System.out.println("element:"+element + ",size:"+queue.size());

        String peek = queue.peek();//获取元素,但是不删除,如果没有可用元素返回null
        System.out.println("peek:"+peek + ",size:"+queue.size());

        queue.clear();

        String poll = queue.poll();//获取并移除,如果没有可用元素返回null
        System.out.println("poll:"+poll + ",size:"+queue.size());

        //移除队列首元素,如果没有可用元素抛出异常
        String removeObect = queue.remove();
        System.out.println("removeObect:"+removeObect + ",size:"+queue.size());

        boolean remove = queue.remove("queue01");//移除指定元素,如果有元素移除返回true,否则false
        System.out.println("remove:"+remove + ",size:"+queue.size());
    }


    @Test
    public void concurrentLinkQueueTest02() {

        AbstractQueue<String> queue = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.add("concurrent-"+i);
        }

        System.out.println(queue);
        System.out.println(queue.size());

        System.out.println(queue.poll());
        System.out.println(queue.size());

        System.out.println(queue.peek());
        System.out.println(queue.size());
    }



    static AbstractQueue<String> ticketsQueue = new LinkedBlockingQueue<>();
    //static AbstractQueue<String> ticketsQueue = new ConcurrentLinkedQueue<>();
    AtomicInteger atomicInteger = new AtomicInteger(0);
    int window = 10;
    CountDownLatch cdh = new CountDownLatch(window);
    int unsafe_counter = 0;

    @Test
    public void concurrentLinkQueueTest03() {

        for (int i = 0; i < 1000; i++) {
            ticketsQueue.add(""+i);
        }

        for (int i = 0; i < window; i++) {
            new Thread(()->{
                while(true){
                    String poll = ticketsQueue.poll();
                    if(null==poll){
                        //cdh.countDown();
                        break;
                    }else{
                        System.out.println(""+poll);
                        //atomicInteger.incrementAndGet();
                        //unsafe_counter++;
                    }
                }
            }).start();
        }

        try {
            //cdh.await();
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println("线程安全的一共为: "+atomicInteger.get());
        //System.out.println("线程非安全一共为:"+unsafe_counter);
    }
}
