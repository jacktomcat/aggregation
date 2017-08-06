package com.gochinatv.cdn.api.jdk.jdk8;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 *
 * 这是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
 *
 * Created by jacktomcat on 17/8/6.
 */
public class OptionalTest {


    @Test
    public void optional() {

        //在之前jdk版本中返回null可以采用这种方式返回
        Optional<Object> result = Optional.empty();


        //调用工厂方法创建Optional实例
        Optional<String> name = Optional.of("hadoop");

        //isPresent方法用来检查Optional实例中是否包含值
        if (name.isPresent()) {
            System.out.println(name.get());//输出hadoop
        }

        name.ifPresent(value-> System.out.println(value));//如果Optional实例有值则为其调用consumer，否则不做处理
        System.out.println(name.orElse("hive"));//如果有值orElse方法会返回Optional实例，否则返回传入的错误信息


        //下面创建了一个不包含任何值的Optional实例
        //例如，值为'null'
        Optional empty = Optional.ofNullable(null);

        //执行下面的代码会输出：No value present
        try {
            //在空的Optional实例上调用get()，抛出NoSuchElementException
            System.out.println(empty.get());
        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }

        //orElseGet与orElse类似，区别在于传入的默认值。
        //orElseGet接受lambda表达式生成默认值。
        System.out.println(empty.orElseGet(()->"test"));

    }

}
