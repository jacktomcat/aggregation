package com.gochinatv.cdn.api.jdk.collect;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * 链表，就是由一个一个结点组成的一类数据结构，跟队列一样用来存放数据，
 * 不过它跟队列不同的就是每一个结点是由数据及对下一个结点的引用两部分组成的，
 * 就是说每一个结点比队列的每一个元素多了一个对下一个结点的引用的“数据”。
 *
 * Created by jacktomcat on 17/8/22.
 */
public class LinkedListTest {


    @Test
    public void testLinkedList() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("test1");
        list.addLast("addLast");

        list.add("add");//添加元素到队尾
        list.push("push");//添加到队头
        list.offer("offer");//添加到链表队尾

        String element = list.element();//获取头元素,但是不删除
        System.out.println(element);//push

        String peek = list.peek();//获取头元素,但是不删除
        System.out.println(peek);//push

        String poll = list.poll();//获取头元素,并删除
        System.out.println(poll);//push

        String pop = list.pop();//从头取出返回,并删除
        System.out.println(pop);//test1

    }

    @Test
    public void testStack(){
        Stack<String> stack = new Stack();
        stack.add("add");
        stack.push("push");
        stack.pop();

    }

    @Test
    public void testCopyOnWriteArrayList(){
        CopyOnWriteArrayList<String> data = new CopyOnWriteArrayList<>();

    }




}
