package com.gochinatv.cdn.api.basic.normal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jacktomcat on 17/8/28.
 */
public class ListTest {


    public static void main(String[] args) {
        List<String> data = new ArrayList<>();
        data.add("1");
        data.add("2");
        data.add("3");
        data.add("4");
        data.add("5");
        data.add("6");
        data.add("7");

        List<String> subList = data.subList(6, 7);
        System.out.println(subList);
    }


}
