package com.gochinatv.cdn.api.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyHandler implements InvocationHandler {

    private Object target;
    public Object init(Object target){

        /**
         * Proxy类是专门完成代理的操作类，可以通过此类为一个或多个接口动态地生成实现类，此类提供了如下的操作方法
         * ClassLoader loader：类加载器
         * Class<?>[] interfaces：得到全部的接口
         * InvocationHandler h：得到InvocationHandler接口的子类实例
         */
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
        //这里的this是当前类，因为他是实现了InvocationHandler，如果写在别的类中，那么只需要new 实现InvocationHandler这个类即可
        //target.getClass().getInterfaces()这个参数 要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)，因为jdk的动态代理需要实现类必须有接口
    }



    @Override
    /**
     * Object proxy：指被代理的对象。
     * Method method：要调用的方法
     * Object[] args：方法调用时所需要的参数
     */
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object result=null;
        System.out.println("------》调用方法开始-----");
        //执行方法
        result=method.invoke(target, args);

        System.out.println("------》调用方法结束-----");

        return result;
    }

}