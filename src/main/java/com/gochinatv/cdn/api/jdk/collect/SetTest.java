package com.gochinatv.cdn.api.jdk.collect;

import org.junit.Test;

import java.util.*;

/**
 * Created by jacktomcat on 17/9/24.
 */
public class SetTest {


    @Test
    public void testSet(){
        Set<String> set = new HashSet<>();

        set.add("1");
        set.add("2");
        set.add("3");
        set.add("4");
        set.add(null);

        System.out.println(set.size());

        for (String s : set) {
            System.out.println(s);
        }

        List<String> retain = new ArrayList<>();
        retain.add("2");
        retain.add("3");

        set.retainAll(retain);//仅仅保留指定集合的这些元素

        System.out.println("==retain 之后==");
        for (String s : set) {
            System.out.println(s);
        }
    }


    @Test
    public void testTreeSet(){
        Set<String> set = new TreeSet<>();

        set.add("1");
        set.add("2");
        set.add("3");
        set.add("4");

        System.out.println(set.size());

        for (String s : set) {
            System.out.println(s);
        }

    }


}
