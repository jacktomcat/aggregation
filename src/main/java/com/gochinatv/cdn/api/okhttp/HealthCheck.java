package com.gochinatv.cdn.api.okhttp;

import com.google.common.base.Preconditions;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ${DESCRIPTION}
 *
 * @auhtor jacktomcat
 * @create 2019-01-08 下午10:30
 */
public final class HealthCheck {

    private static OkHttpClient okHttpClient;
    private static HttpHosts httpHosts;
    private static String strategy; //负载使用的策略
    private static CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet();
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    static {
        if(okHttpClient==null){
            synchronized (OkHttpClient.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient()
                            .newBuilder()
                            .readTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(10,TimeUnit.SECONDS)
                            .connectTimeout(10,TimeUnit.SECONDS).build();
                }
            }
        }
    }

    public static void init() {
        check();
    }

    /**
     * 健康检查
     * 需要2分钟执行一次检查
     */
    public static void check(){
        Preconditions.checkArgument(StringUtils.isEmpty(httpHosts.getUrl()),"调用机器不能为空");
        strategy = httpHosts.getStrategy();
        String url = httpHosts.getUrl();
        String[] hosts = url.split(",");
        Arrays.stream(hosts).forEach(host -> {
            executorService.submit(()-> {
                Request request = new Request.Builder().url(host).build();
                try {
                    Response execute = okHttpClient.newCall(request).execute();
                    if(execute.code()== HttpURLConnection.HTTP_OK) {
                        set.add(host);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
    }


    /**
     * 获取主机
     * @return
     */
    public static String getHost() {
        if (set.isEmpty()) {
            System.out.println("没有可用的服务器{}....."+httpHosts.getUrl());
            return null;
        }
        
        return "";
    }


}
