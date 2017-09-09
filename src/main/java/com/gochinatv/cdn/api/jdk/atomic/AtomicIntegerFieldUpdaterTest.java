package com.gochinatv.cdn.api.jdk.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;


/**
 *
 *
 * Created by jacktomcat on 17/9/9.
 */
public class AtomicIntegerFieldUpdaterTest {

    //对已经new出来的某个变量进行修改，保证其原子性。
    //AtomicIntegerFieldUpdater使用最重要的在于其构造函数
    AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterUser> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterUser.class,"id");//id字段必须是volatile

    AtomicIntegerFieldUpdaterUser user = new AtomicIntegerFieldUpdaterUser(1,"张三");//实例化

    @Test
    public void test() {
        updater.set(user, 10);
        int id = updater.get(user);
        System.out.println("id:" + id);//10

        int addAndGet = updater.addAndGet(user, 20); //加20
        System.out.println("addAndGet id:" + addAndGet);//30

        //此时的原有值为30 ,期望为 20   修改为40 ,必然不成立
        boolean compareAndSet = updater.compareAndSet(user, 20, 40);
        System.out.println("compareAndSet:" + compareAndSet);
        System.out.println("id:" + updater.get(user));//30,没有更新成功


        //此时的原有值为30 ,期望为 30   修改为40 ,更新成功
        compareAndSet = updater.compareAndSet(user, 30, 40);
        System.out.println("compareAndSet:" + compareAndSet);
        System.out.println("id:" + updater.get(user));//40,更新成功


    }

}



class AtomicIntegerFieldUpdaterUser {

    public volatile  int id;
    public String name;

    public AtomicIntegerFieldUpdaterUser(int id, String name) {
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


}
