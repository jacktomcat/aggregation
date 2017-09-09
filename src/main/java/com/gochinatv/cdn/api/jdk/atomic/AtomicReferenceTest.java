package com.gochinatv.cdn.api.jdk.atomic;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;

/**
 *  涉及到  UnaryOperator 一元操作的用法  jdk8的新特性
 *
 *  http://www.concretepage.com/java/jdk-8/java-8-unaryoperator-binaryoperator-example
 *
 *  Created by jacktomcat on 17/9/9.
 */
public class AtomicReferenceTest {

    AtomicReferenceUser user1 = new AtomicReferenceUser(1,"张三");
    AtomicReferenceUser user2 = new AtomicReferenceUser(2,"李四");

    AtomicReference<AtomicReferenceUser> reference = new AtomicReference(user1);

    @Test
    public void test(){
        AtomicReferenceUser getUser = reference.get();
        System.out.println("id:"+getUser.getId()+",name:"+getUser.getName());//id:1,name:张三

        reference.set(user2);
        getUser = reference.get();
        System.out.println("id:"+getUser.getId()+",name:"+getUser.getName());//id:2,name:李四

        getUser = reference.getAndSet(user1);
        System.out.println("id:"+getUser.getId()+",name:"+getUser.getName());//id:2,name:李四

        getUser = reference.get();
        System.out.println("id:"+getUser.getId()+",name:"+getUser.getName());//id:1,name:张三

        boolean compareAndSet = reference.compareAndSet(user1, user2);//期望值为user1,原始值为 user1

        System.out.println("compareAndSet:"+compareAndSet);//true
        getUser = reference.get();
        System.out.println("id:"+getUser.getId()+",name:"+getUser.getName());//id:2,name:李四


        AtomicReferenceUser user3 = new AtomicReferenceUser(3,"王五");

        //一元操作,给定的user对象id+100,name+100
        UnaryOperator<AtomicReferenceUser> operator1 = user->AtomicReferenceUser.updateUser(user);//这个做了部分修改
        UnaryOperator<AtomicReferenceUser> operator2 = user->user;//这个没做任何修改
        AtomicReferenceUser andUpdate = reference.getAndUpdate(operator1);

        System.out.println("id:"+andUpdate.getId()+",name:"+andUpdate.getName());//id:102,name:李四100

    }

}

/**
 * 引用实体
 */
class AtomicReferenceUser {

    private int id;
    private String name;

    public AtomicReferenceUser(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static AtomicReferenceUser updateUser(AtomicReferenceUser user){
        user.setId(user.getId()+100);
        user.setName(user.getName()+"100");
        return user;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AtomicReferenceUser that = (AtomicReferenceUser) o;

        if (id != that.id) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }*/

}


    /**
        // 使用 null 初始值创建新的 AtomicReference。
        AtomicReference()

        // 使用给定的初始值创建新的 AtomicReference。
        AtomicReference(V initialValue)

        // 如果当前值 == 预期值，则以原子方式将该值设置为给定的更新值。
        boolean compareAndSet(V expect, V update)

        // 获取当前值。
        V get()

        // 以原子方式设置为给定值，并返回旧值。
        V getAndSet(V newValue)

        // 最终设置为给定值。
        void lazySet(V newValue)

        // 设置为给定值。
        void set(V newValue)

        // 返回当前值的字符串表示形式。
        String toString()

        // 如果当前值 == 预期值，则以原子方式将该值设置为给定的更新值。
        boolean weakCompareAndSet(V expect, V update)
     */