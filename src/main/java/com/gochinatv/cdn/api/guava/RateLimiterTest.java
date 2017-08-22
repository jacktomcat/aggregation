package com.gochinatv.cdn.api.guava;

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
            System.out.println("call execute.." + i);

        }
        Long end = System.currentTimeMillis();

        System.out.println(end - start);

    }

    public void testWithRateLimiter() {
        Long start = System.currentTimeMillis();
        RateLimiter limiter = RateLimiter.create(10.0); // 每秒不超过10个任务被提交
        for (int i = 0; i < 10; i++) {
            limiter.acquire(); // 请求RateLimiter, 超过permits会被阻塞
            System.out.println("call execute.." + i);

        }
        Long end = System.currentTimeMillis();

        System.out.println(end - start);

    }

}
