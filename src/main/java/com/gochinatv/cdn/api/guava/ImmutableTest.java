package com.gochinatv.cdn.api.guava;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.util.HashSet;


/**
 *
 * 　　不可变集合，顾名思义就是说集合是不可被修改的。集合的数据项是在创建的时候提供，并且在整个生命周期中都不可改变。
 *
 *　　为什么要用immutable对象？immutable对象有以下的优点：
 *　　　　1.对不可靠的客户代码库来说，它使用安全，可以在未受信任的类库中安全的使用这些对象
 *　　　　2.线程安全的：immutable对象在多线程下安全，没有竞态条件
 *　　　　3.不需要支持可变性, 可以尽量节省空间和时间的开销. 所有的不可变集合实现都比可变集合更加有效的利用内存 (analysis)
 *　　　　4.可以被使用为一个常量，并且期望在未来也是保持不变的
 *
 *　　immutable对象可以很自然地用作常量，因为它们天生就是不可变的对于immutable对象的运用来说，它是一个很好的防御编程（defensive programming）的技术实践。
 *
 * Created by jacktomcat on 17/8/2.
 */
public class ImmutableTest {

    @Test
    public void immutableList() {
        //ImmutableList.copyOf(new ArrayList<Object>())  初始化
        ImmutableList<String> list = ImmutableList.of("张三","李四","王五");
        System.out.println(list);

        list.reverse().forEach(v-> System.out.println(v));//java8 的迭代方式
        list.forEach(v-> System.out.println(v));//java8 的迭代方式

        //TODO java8 stream
        //list.stream().
    }



    @Test
    public void immutableMap() {
        //ImmutableMap.copyOf(new HashedMap<String,Object>())  //初始化
        ImmutableMap<String,Object> map = ImmutableMap.of("name","张山","age",23);

        map.forEach((k,v)->{//java8 迭代
            System.out.println(k + "," + v);
        });
    }


    @Test
    public void immutableSet() {
        //ImmutableSet.copyOf(new HashSet<String>()) //初始化

        ImmutableSet<String> set = ImmutableSet.of("张三","李四");
        set.forEach(v-> System.out.println(v));//java8 迭代

        //filter过滤
        set.stream().filter(v->v.equals("张三")).forEach(System.out::println);
    }



    //java8的 Stream是“延迟计算”（lazy）

}
