package com.gochinatv.cdn.api.proxy;

public class UserServiceImpl implements UserService{//实现类

    @Override
    public void addUser(String name, String password) {
        System.out.println("++++执行addUser()方法++++++");
    }

    @Override
    public void getUser(String id) {
        System.out.println("++++执行getUser()方法++++++");
    }
}