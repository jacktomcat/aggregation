package com.gochinatv.cdn.api.jdk.queue;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by jacktomcat on 17/9/24.
 * 双端队列
 */
public class LinkedBlockingDequeTest {


    @Test
    public void linkedBlockingDequeTest() {
        BlockingDeque queue = new LinkedBlockingDeque<>();
        boolean add = queue.add("add");


    }


    @Test
    public void arrayDequeTest() {
        Deque queue = new ArrayDeque<>();
        boolean add = queue.add("add");


    }


}
