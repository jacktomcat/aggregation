package com.gochinatv.cdn.api.basic.exception;

/**
 * ${DESCRIPTION}
 *
 * @auhtor jacktomcat
 * @create 2019-01-13 下午12:21
 */
public class ExceptionTest {


    public static void main(String[] args) {
        boolean flag = true;

        // throw new Exception(""); 是需要捕获异常
        if (flag) {
            try {
                throw new Exception("不能初始化!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        //这里不需要捕获异常,所以抛出jvm就停止了
        if (flag) {
            throw  new IllegalArgumentException("不能初始化!");
        }

        System.out.println("抛出异常后执行...........");

    }




}
