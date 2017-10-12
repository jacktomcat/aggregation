package com.gochinatv.cdn.api.zipkin;

import com.github.kristofa.brave.Brave;

/**
 *
 * 分布式日志跟踪
 * Created by jacktomcat on 17/9/29.
 */
public class LogTrace {


    public static void main(String[] args) {

        Brave.Builder builder = new Brave.Builder("serviceName");

    }


}
