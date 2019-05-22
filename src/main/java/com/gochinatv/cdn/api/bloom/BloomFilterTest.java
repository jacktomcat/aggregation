package com.gochinatv.cdn.api.bloom;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.HashMap;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @auhtor jacktomcat
 * @create 2019-03-10 下午4:47
 */
public class BloomFilterTest {


    private static int size = 10000000;

    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size);



    public static void main(String[] args) {
        //bloomContains();
        mapContains();
    }


    public static void bloomContains() {
        long startTime = System.nanoTime(); // 获取开始时间
        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }

        //判断这一百万个数中是否包含29999这个数
        if (bloomFilter.mightContain(29999)) {
            System.out.println("命中了");
        }
        long endTime = System.nanoTime();   // 获取结束时间

        System.out.println("程序运行时间： " + (endTime - startTime) + "纳秒");
    }


    public static void mapContains() {
        long startTime = System.nanoTime(); // 获取开始时间
        Map<Integer,Integer> data = new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            data.put(i,i);
        }


        //判断这一百万个数中是否包含29999这个数
        if (data.containsKey(29999)) {
            System.out.println("命中了");
        }
        long endTime = System.nanoTime();   // 获取结束时间

        System.out.println("程序运行时间： " + (endTime - startTime) + "纳秒");

    }

}
