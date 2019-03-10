package com.gochinatv.cdn.api.guava.concurrent;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by jacktomcat on 17/8/22.
 */
public class RateLimiterTest {


    @Test
    public void test(){

        //testNoRateLimiter();
        testWithRateLimiter();
    }


    public void testNoRateLimiter() {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            System.out.println("call execute.." + i + ", current time :"+System.currentTimeMillis());

        }
        Long end = System.currentTimeMillis();

        System.out.println(end - start);//这里基本没有任何时间间隔

    }

    public void testWithRateLimiter() {
        Long start = System.currentTimeMillis();
        RateLimiter limiter = RateLimiter.create(10.0); // 每秒不超过10个任务被提交,平均每个任务0.1秒
        int acquire = 2;
        for (int i = 0; i < 20; i++) {
            limiter.acquire(acquire);// 请求RateLimiter, 超过permits会被阻塞

            System.out.println("等待时间:" + acquire+ ", call execute.." + i + ", current time:"+System.currentTimeMillis());

        }

        /**
         * 因为这里设置的是10个令牌,所以平均到1秒种,也就是每个0.1秒(100ms)1个令牌,所以如果时间小于100ms,下面的limiter.tryAcquire()获取到的为false
         * 如果超过100ms,那么获取到的为true,说明拿到了令牌,
         *
         */

        boolean isAcquire = limiter.tryAcquire();//这里可以在并发场景中用做服务降级处理
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
