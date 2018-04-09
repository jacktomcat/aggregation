package com.gochinatv.cdn.api.basic.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ${DESCRIPTION}
 *
 * @auhtor jacktomcat
 * @create 2018-03-22 下午11:09
 */
public class ReflectTest{

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {

        ReflectUser user = new ReflectUser();
        user.setId(1);
        user.setName("张三");


        //Class<ReflectUser> clazz = ReflectUser.class;
        Class clazz = user.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);//如果fidle 是private声明的那么这里必须要设置可访问,否则报错

            Object o = declaredField.get(user);

            System.out.println(declaredField.getName() + ":" + o);
        }

        System.out.println("通过get方式获取值:");

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if(method.getName().startsWith("get")){
                if(!method.isAccessible()){
                    method.setAccessible(true);//如果method是private声明的那么这里必须要设置可访问,否则报错
                }
                Object invoke = method.invoke(user);
                System.out.println(method.getName() + "():"+invoke);
            }
        }


    }




}
