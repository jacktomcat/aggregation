package com.gochinatv.cdn.api.jdk.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Java程序经常会遇到进程挂掉的情况，一些状态没有正确的保存下来，这时候就需要在JVM关掉的时候执行一些清理现场的代码。JAVA中的ShutdownHook提供了比较好的方案。
 *
 * JDK提供了Java.Runtime.addShutdownHook(Thread hook)方法，可以注册一个JVM关闭的钩子，这个钩子可以在一下几种场景中被调用：
 *
 * 1. 程序正常退出
 * 2. 使用System.exit()
 * 3. 终端使用Ctrl+C触发的中断
 * 4. 系统关闭
 * 5. OutOfMemory宕机
 * 6. 使用Kill pid命令干掉进程（注：在使用kill -9 pid时，是不会被调用的）
 */
public class ExecutorTest {

    static ExecutorService executorService = Executors.newFixedThreadPool(1);

    static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
    static int counter = 0;

    public static void main(String[] args) {

        //scheduleAtFixedRateTest();
        //scheduleWithFixedDelayTest();

        executorServiceTest();
    }


    public static void executorServiceTest(){
        Runtime.getRuntime().addShutdownHook(new Thread(() ->{
            System.out.println("shutdown.......");
        }));

        while(true) {
            executorService.submit(() -> {
                doTask("executorServiceTest");
            });

            if (counter==3) {
                executorService.shutdownNow();
            }
        }
    }


    public static void scheduleWithFixedDelayTest(){
        scheduledExecutorService.scheduleWithFixedDelay(() ->{
            try{
                doTask("scheduleWithFixedDelayTest");
            }catch (Exception e) {

            }
        },2000,2000, TimeUnit.MILLISECONDS);
    }



    public static void scheduleAtFixedRateTest(){
        scheduledExecutorService.scheduleAtFixedRate(() ->{
            try{
                doTask("scheduleAtFixedRateTest");
            }catch (Exception e) {
                System.out.println("异常"+e);
            }
        },2000,2000, TimeUnit.MILLISECONDS);
    }



    public static void doTask(String type) {
        System.out.println("counter:"+counter);
        long start = System.currentTimeMillis();
        System.out.println(type + ":" +start);
        doSubTask();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        counter++;
        System.out.println((System.currentTimeMillis()-start)/1000);
    }


    public static void doSubTask() {
        if (counter==3) {
            //try {
                throw new RuntimeException("自定义抛出异常");
            /*}catch (Exception e) {

            }*/
        }
    }


}
