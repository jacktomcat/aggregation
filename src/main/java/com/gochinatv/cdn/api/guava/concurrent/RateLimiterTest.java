package com.gochinatv.cdn.api.guava.concurrent;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

/**
 * Created by jacktomcat on 17/8/22.
 */
public class RateLimiterTest {


    @Test
    public void test(){

        testNoRateLimiter();
        testWithRateLimiter();
    }


    public void testNoRateLimiter() {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            System.out.println("call execute.." + i + ", current time :"+System.currentTimeMillis());

        }
        Long end = System.currentTimeMillis();

        System.out.println(end - start);

    }

    public void testWithRateLimiter() {
        Long start = System.currentTimeMillis();
        RateLimiter limiter = RateLimiter.create(100.0); // 每秒不超过10个任务被提交
        for (int i = 0; i < 10; i++) {
            limiter.acquire(); // 请求RateLimiter, 超过permits会被阻塞
            System.out.println("call execute.." + i + ", current time:"+System.currentTimeMillis());

        }

        // 这里不能重复使用acquire
        boolean isAcquire = limiter.tryAcquire();//
        if(isAcquire){
            //TODO 获取到资源 处理业务逻辑
            System.out.println("获取到资源");
        }else{
            //TODO 没有获取到资源 可以返回50x错误
            System.out.println("暂时服务器繁忙");
        }

        Long end = System.currentTimeMillis();

        System.out.println(end - start);

    }

}
