package com.gochinatv.cdn.api.jdk.collect;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

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
