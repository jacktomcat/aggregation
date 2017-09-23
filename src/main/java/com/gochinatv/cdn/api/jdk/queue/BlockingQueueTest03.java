package com.gochinatv.cdn.api.jdk.queue;


import org.junit.Test;

import java.util.AbstractQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author jacktomcat
 *
 */
public class BlockingQueueTest03 {


    @Test
    public void concurrentLinkQueueTest() {

        AbstractQueue<String> queue = new ConcurrentLinkedQueue<>();

        //添加元素到队列,有可用空间添加并返回true,否则抛出异常
        boolean add = queue.add("queue01");
        System.out.println("add:"+add + ",size:"+queue.size());
        
        boolean add2 = queue.add("queue02");
        System.out.println("add2:"+add2 + ",size:"+queue.size());
        
        //添加元素到队列,添加成功返回true,否则false
        boolean offer = queue.offer("queue03");
        System.out.println("offer:"+offer + ",size:"+queue.size());

        
        //获取元素
        queue.element();//获取元素,但是不删除,如果没有可用元素,抛出异常
        queue.peek();//获取元素,但是不删除,如果没有可用元素返回null
        queue.poll();//获取并移除,如果没有可用元素返回null
        
        //移除队列首元素,如果没有可用元素抛出异常
        String removeObect = queue.remove();
        
        boolean remove = queue.remove("queue03");//移除指定元素
    }


}
