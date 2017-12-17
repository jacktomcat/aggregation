package com.gochinatv.cdn.api.jdk.jdk8.lambda;

/**
 * Created by jacktomcat on 17/12/17.
 */
public class RunnableTest {


    public static void main(String[] args) {

        //jdk8之前
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        //jdk8
        new Thread(()->{

        });
    }

}
