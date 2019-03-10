package com.gochinatv.cdn.api.jdk.jvm;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * ${DESCRIPTION}
 *
 * @auhtor jacktomcat
 * @create 2019-01-17 下午10:22
 */
public class PermGenTest {


    private static final int _1M = 1024 * 1024;
    public static void main(String[] args) throws InterruptedException {

        byte[] s0 = new byte[2 * _1M];
        byte[] s1 = new byte[2 * _1M];
        byte[] s2 = new byte[2 * _1M];
        byte[] s3 = new byte[2 * _1M];
        byte[] s4 = new byte[2 * _1M];
        // 以上总共增加10M，触发minor GC, 通过GC不难发现，这10M的内存有eden和tenured分摊
        // 此时，新增一个1M的内存 , 再次触发minor GC
        byte[] s5 = new byte[1 * _1M];
        // 新增一个1M的内存，由eden去进行担保
        // 此时再新增一个7M的对象,将导致OOM的发生，Eden区无法存储。eden仍然分担3M的内容，而剩下的15M老年代担保失败，触发Full GC，GC后仍然无法存储，发生OOM
        byte[] s6 = new byte[7 * _1M];

    }


}
