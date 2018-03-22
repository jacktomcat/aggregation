package com.gochinatv.cdn.api.jdk.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * ${DESCRIPTION}
 *
 * 参考
 * http://www.importnew.com/20331.html
 *
 * @auhtor jacktomcat
 * @create 2018-03-22 下午10:33
 */
public class ParametersTest {

    public static void main(String[] args) {
        int[] arrays = new int[]{1,2,3,4,5};

        IntStream intStream = Arrays.stream(arrays).filter(ParametersTest::compare)
                .flatMap(p -> Arrays.stream(new int[]{p}));
        intStream.forEach(v-> System.out.println(v));

        String[] words = new String[]{"Hello","World"};

        List<String[]> collect = Arrays.stream(words)
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());
        collect.stream().forEach(a->{
            Arrays.stream(a).forEach(aa->{
                System.out.println(aa);
            });
        });
    }


    public static boolean compare(int params){
        return params<4;
    }

    static class Params{
        int params;

        public Params(int params){
            this.params = params;
        }
    }

}
