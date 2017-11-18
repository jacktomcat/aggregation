package com.gochinatv.cdn.api.okhttp;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhuhuihui on 17/11/17.
 */
public class OkhttpUtil {


    public static OkhttpUtil okhttpUtil;
    private static OkHttpClient okHttpClient;

    private OkhttpUtil(){}

    public static OkhttpUtil getInstance(){

        if (okhttpUtil == null) {
            synchronized (OkhttpUtil.class) {
                if (okhttpUtil == null) {
                    okhttpUtil = new OkhttpUtil();
                }
            }
        }
        return okhttpUtil;
    }

    public static  OkHttpClient getOkHttpClient(){
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
        return okHttpClient;
    }


    public static String post(String url){
        FormBody body = new FormBody.Builder().add("name","张三").build();

        Request request = new Request.Builder().url(url).post(body).build();
        try {
            try (Response response = getOkHttpClient().newCall(request).execute()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
