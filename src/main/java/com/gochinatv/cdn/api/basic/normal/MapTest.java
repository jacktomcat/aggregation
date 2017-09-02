package com.gochinatv.cdn.api.basic.normal;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by jacktomcat on 17/8/28.
 */
public class MapTest {


    public static void main(String[] args) {

        Map<String,String> data = new HashMap<>();
        for(int i=0;i<10;i++){
            data.put("key:"+i,"value:"+i);
        }

        data.forEach((k,v)->{
            System.out.println("k="+k+",v="+v+",size="+data.size());
            data.remove(k);
        });

    }


}
