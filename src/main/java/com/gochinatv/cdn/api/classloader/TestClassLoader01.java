package com.gochinatv.cdn.api.classloader;

/**
 * Created by jacktomcat on 17/10/3.
 */
public class TestClassLoader01 {

    public static void main(String[] args){
        //System.out.println(ClassLoader.getSystemClassLoader());
        TestClassLoader01 c = new TestClassLoader01();
        //Class<?> c = Class.forName("com.gochinatv.cdn.api.basic.string.StringTest");
       // System.out.println(c.getClassLoader());
        //System.out.println(c.getClassLoader().getParent());
        //System.out.println(c.getClassLoader().getParent().getParent());
        //System.out.println(TestClassLoader01.class.getClassLoader());
    }
}
