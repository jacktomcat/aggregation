package com.gochinatv.cdn.api.basic.normal;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by jacktomcat on 17/8/28.
 */
public class MapTest {


    public static void main(String[] args) {

        Map<String,String> data = new HashMap<>();

        //下面几种一样的表达结果
        Supplier<String> validate = () -> "不能为空";

        Objects.requireNonNull(data, validate);
        Objects.requireNonNull(data, ()->message());
        Objects.requireNonNull(data, MapTest::message);

        /*for(int i=0;i<10;i++){
            data.put("key:"+i,"value:"+i);
        }

        data.forEach((k,v)->{
            System.out.println("k="+k+",v="+v+",size="+data.size());
            data.remove(k);
        });*/

        data.put("name","张三");
        data.replace("name","李四");
        boolean replace = data.replace("name", "李四", "张三1");

        //如果存在这个key,那么直接用biFunction的值替换,否则添加一个item值为biFunction的值
        data.compute("name",(k,v)->"zhuhh");

        //String vs = data.putIfAbsent("name1","111");

        //如果key存在返回指定的jackjboss,如果key不存在,则添加
        //String vs = data.computeIfAbsent("name1", (k) ->"jackjboss");
        //System.out.println(vs);

        //如果key存在返回指定的jackjboss,如果key不存在,返回null,删除原来的
        String vs = data.computeIfPresent("name",(k,v)->"jackjboss");
        System.out.println(vs);

        //如果存在这个key,那么直接用biFunction的值替换,否则添加一个item值为 value(aaaa)
        //data.merge("name1","aaaa",(k,v)->"abc");

        data.forEach((k,v)->{
            System.out.println("k="+k+",v="+v+",size="+data.size());
        });


    }


    public static  String message(){
        return "不能为空";
    }

}
