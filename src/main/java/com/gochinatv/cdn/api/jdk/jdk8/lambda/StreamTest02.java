package com.gochinatv.cdn.api.jdk.jdk8.lambda;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * Created by jacktomcat on 18/1/9.
 */
public class StreamTest02 {


    public static void main(String[] args) {

        List<Stream2> streamList = new ArrayList<>();
        streamList.add(new Stream2(1,"张三"));
        streamList.add(new Stream2(10,"张三"));
        streamList.add(new Stream2(2,"张三2"));
        streamList.add(new Stream2(20,"张三3"));
        streamList.add(new Stream2(3,"张三4"));

        //.filter(s -> s.getId() > 2)
        streamList = streamList.stream().sorted(Comparator.comparing(Stream2::getId)).filter(distinctByKey(Stream2::getName)).collect(Collectors.toList());

        streamList.forEach(s-> System.out.println(s.getId()));

    }



    public static <T> Predicate<T> distinctByKey(Function<? super T,?> function){
        Set<Object> set = new HashSet<>();
        return t->set.add(function.apply(t));
    }

}



class Stream2{

    private int id;
    private String name;

    public Stream2(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
