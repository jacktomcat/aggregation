package com.gochinatv.cdn.api.basic.string;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jacktomcat on 17/7/2.
 *
 * http://www.cnblogs.com/wanlipeng/archive/2010/10/21/1857513.html
 */
public class StringTest {

	//Instant 
	//LocalDateTime
	//https://my.oschina.net/u/3746673/blog/1594197
	
     public static void  main(String[] args){
    	 
         String name = new String("Ext");
         String name1 = "abc";
         String name2 = "a";
         String name3 = "bc";
         String name4 = name2 + name3;
         String name5 = "a"+"bc";

         if(name.intern()=="Ext"){
             System.out.println("equals Ext");//true
         }

         System.out.println(name1==name4); //false

         System.out.println(name1==name5);//true
         
         
         List<String> params = new ArrayList<String>();
         params.add("1");
         params.add("2");
         params.add("3");
         params.add("4");
         
         List<String> subList = params.subList(0, 2);
         subList.add("55");
         System.out.println(subList);
         System.out.println(params);
         
         //subList.toArray(a)
         
     }

}
