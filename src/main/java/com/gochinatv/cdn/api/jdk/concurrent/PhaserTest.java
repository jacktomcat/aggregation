package com.gochinatv.cdn.api.jdk.concurrent;

import org.junit.Test;

import java.util.concurrent.Phaser;


/**
 * 此外，CyclicBarrier、CountDownLatch需要在初始化的构造函数中指定同步者的个数，
 * 且运行时无法再次调整
 */
public class PhaserTest {

    @Test
    public void phaser() {
        Phaser phaser = new Phaser();
        phaser.register();//parties count: 1

        phaser.arriveAndDeregister();//count : 0;

        //phaser.awaitAdvance()
        //phaser.arrive()


    }


}
