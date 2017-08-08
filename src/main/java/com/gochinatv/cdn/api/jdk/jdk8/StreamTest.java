package com.gochinatv.cdn.api.jdk.jdk8;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 *
 * 流的基本概念
 *   流是Java8引入的全新概念，它用来处理集合中的数据，暂且可以把它理解为一种高级集合。
 *   众所周知，集合操作非常麻烦，若要对集合进行筛选、投影，需要写大量的代码，而流是以声明的形式操作集合，它就像SQL语句，
 *   我们只需告诉流需要对集合进行什么操作，它就会自动进行操作，并将执行结果交给你，无需我们自己手写代码。
 *
 *   因此，流的集合操作对我们来说是透明的，我们只需向流下达命令，它就会自动把我们想要的结果给我们。
 *   由于操作过程完全由Java处理，因此它可以根据当前硬件环境选择最优的方法处理，
 *   我们也无需编写复杂又容易出错的多线程代码了。
 *
 *
 * Created by jacktomact on 17/8/3.
 */
public class StreamTest {

    /**
     * 获取流的几种方式:
     *   1:集合
     *         这种数据源较为常用，通过stream()方法即可获取流对象
     *         List<Person> list = new ArrayList<Person>();
     *         Stream<Person> stream = list.stream();
     *
     *   2:数组
     *         通过Arrays类提供的静态函数stream()获取数组的流对象
     *         String[] names = {"chaimm","peter","john"};
     *         Stream<String> stream = Arrays.stream(names);
     *
     *   3: 值
     *         直接将几个值变成流对象
     *         Stream<String> stream = Stream.of("chaimm","peter","john");
     *
     *   4: 文件
     *         Files.lines(Paths.get("/"))
     *
     */

    @Test
    public void stream() {
        List<String> result = Lists.newArrayList();
        result.add("java");
        result.add("c#");
        result.add("spring");
        result.add("spring");
        result.add("spring");
        result.add("hadoop");
        result.add("hibernate");
        result.add("hive");
        result.add("sqoop");
        result.add("html");
        result.add("mysql");

        result.stream().forEach(System.out::println);

        //findFirst 首元素
        System.out.println("findFirst: " +result.stream().findFirst().get());

        System.out.println("==========distinct 去重============");
        result.stream().distinct().filter(v->v.length()>4).
                forEach(v->System.out.println("value length>4 :" + v));

        System.out.println("==========skip,limit,forEachOrdered============");
        result.stream().distinct().limit(5).skip(2).forEachOrdered(System.out::println);


        System.out.println("==========map 全部转为大写============");
        result.stream().distinct().map(String::toUpperCase).forEach(System.out::println);


        System.out.println("==========parallelStream是否包含某个元素anyMatch============");
        boolean anyMatch = result.parallelStream().anyMatch(v -> v.contains("spring"));
        System.out.println(anyMatch);

        System.out.println("==========parallelStream是否全部包含元素allMatch============");
        boolean allMatch = result.parallelStream().allMatch(v -> v.contains("spring"));
        System.out.println(allMatch);

        //findAny  findAny能够从流中随便选一个元素出来，它返回一个Optional类型的元素。
        //noneMatch noneMatch与allMatch恰恰相反，它用于判断流中的所有元素是否都不满足指定条件

        long count = result.parallelStream().count();
        System.out.println("count: "+count);


        //将普通流转换成数值流  StreamAPI提供了三种数值流
        // IntStream、DoubleStream、LongStream,
        // 也提供了将普通流转换成数值流的三种方法：mapToInt、mapToDouble、mapToLong
        IntStream intStream = result.stream().mapToInt(String::length);
        System.out.println(intStream.average());

        //max
        OptionalInt max = result.stream().mapToInt(String::length).max();
        System.out.println("max: " + max);

        OptionalInt min = result.stream().mapToInt(String::length).min();
        System.out.println("min: " + min);


        int sum = result.stream().mapToInt(String::length).sum();
        System.out.println("sum: " + sum);

        int reduce = result.stream().mapToInt(String::length).reduce(10, Integer::sum);
        OptionalInt reduce1 = result.stream().mapToInt(String::length).reduce((len1, len2) -> len1 * 2 + len2 * 2);
        System.out.println("reduce sum : " + reduce);
        System.out.println("reduce len1 len2 : " + reduce1.getAsInt());


        //Collectors 用法
        //string to map
        Map<Integer, List<String>> collect =
                result.stream().collect(Collectors.groupingBy(v -> v.length()));

    }
    
    
    public static void main(String[] args) {
    	String[] language = {"spring","hibernate","hadoop","spark"};
    	
    	// 这里主要强调了   StreamTest::filterPrefix 和  v->StreamTest.filterPrefix(v)  用法区别
		Arrays.stream(language).filter(StreamTest::filterPrefix).forEach(System.out::println);
		Arrays.stream(language).filter(v->StreamTest.filterPrefix(v)).forEach(System.out::println);
	}
    
    public static boolean filterPrefix(String text){
    	if(text.startsWith("h")){
    		return true;
    	}
    	return false;
    }
    
}
