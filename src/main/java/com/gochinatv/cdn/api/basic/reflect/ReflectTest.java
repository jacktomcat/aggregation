package com.gochinatv.cdn.api.basic.reflect;

import sun.reflect.Reflection;

import java.lang.reflect.Field;

/**
 * ${DESCRIPTION}
 *
 * @auhtor jacktomcat
 * @create 2018-03-22 下午11:09
 */
public class ReflectTest{

    public static void main(String[] args) throws IllegalAccessException {

        ReflectUser user = new ReflectUser();
        user.setId(1);
        user.setName("张三");


        Class<ReflectUser> clazz = ReflectUser.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName());

            declaredField.setAccessible(true);
            Object o = declaredField.get(user);

            System.out.println(o);

            Field[] fields = user.getClass().getFields();
        }

    }




}
