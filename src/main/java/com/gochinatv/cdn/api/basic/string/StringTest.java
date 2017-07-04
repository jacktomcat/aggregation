package com.gochinatv.cdn.api.basic.string;

/**
 * Created by jacktomcat on 17/7/2.
 *
 * http://www.cnblogs.com/wanlipeng/archive/2010/10/21/1857513.html
 */
public class StringTest {


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

     }

}
