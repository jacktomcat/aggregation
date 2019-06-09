package com.gochinatv.cdn.api.proxy;

public class Client {


    public static void main(String[] args) {

        ProxyHandler proxy=new ProxyHandler();//调用代理类
        UserService service=(UserService)proxy.init(new UserServiceImpl());
        service.addUser("jackjboss","12345");

    }


}
