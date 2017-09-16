package com.gochinatv.cdn.api.jdk.collect;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.BiFunction;

/**
 * Created by jacktomcat on 17/9/11.
 */
public class MapTest {


    @Test
    public void testHashMap(){
        Map<String,String> data = new HashMap<>();

    }


    @Test
    public void testLinkedHashMap(){
        Map<String,String> data = new LinkedHashMap<>();

        data.put("1111","1111");
    }


    @Test
    public void testHashTable(){
        Hashtable<String,String> table = new Hashtable<>();
        table.put("222","222");

    }


    @Test
    public void testSortedMap(){
        SortedMap<String,String> data = new TreeMap<>();
        data.put("bbb", "aaa");
        data.put("cc", "bbb");
        data.put("abc", "ccc");
        data.put("aaa", "cdc");
        data.put("aaa", "cdc");

        data.forEach((k,v)->{
            System.out.println(k+"===="+v);
        });


        SortedMap<Integer,String> data1 = new TreeMap<>();
        data1.put(23, "aaa");
        data1.put(98, "bbb");
        data1.put(11, "ccc");
        data1.put(134, "cdc");

        data1.forEach((k,v)->{
            System.out.println(k+"===="+v);
        });
    }


    @Test
    public void testConcurrentHashMap(){
        Map<String,String> data = new ConcurrentHashMap<>(100);
        data.put("name","12");
        String name = data.merge("name", "34", String::concat);

        System.out.println("name="+name);

        data.forEach((k,v)->{
            System.out.println("key="+k+",value="+v);
        });

        BiFunction<Integer,Integer,Integer> fun = (first,last)->first*last;

        Map<String,Integer> data1 = new ConcurrentHashMap<>(100);
        data1.merge("name",34,fun);
        data1.forEach((k,v)->{
            System.out.println("key="+k+",value="+v);
        });
    }


    @Test
    public void testConcurrentSkipListMap(){
        ConcurrentSkipListMap data = new ConcurrentSkipListMap();
        
    }


}
