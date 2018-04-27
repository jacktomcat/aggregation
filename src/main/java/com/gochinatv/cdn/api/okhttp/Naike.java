package com.gochinatv.cdn.api.okhttp;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import javax.net.ssl.*;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import static com.gochinatv.cdn.api.okhttp.OkhttpLoginGithub.loginUrl;


/**
 * ${DESCRIPTION}
 *
 * @auhtor zhuhuihui
 * @create 2018-03-28 下午10:39
 */
public class Naike {


    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private final static Map<String, List<Cookie>> cookiesMap = new HashMap<String, List<Cookie>>();

    private static TrustAllCerts trust = new TrustAllCerts();

    static OkHttpClient okHttpClient = new OkHttpClient()
            .newBuilder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS)
            .connectTimeout(30,TimeUnit.SECONDS).cookieJar(setCookieJar())
            .sslSocketFactory(createSSLSocketFactory(),trust)
            .hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            })
            .build();

    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{trust}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }


    public static CookieJar setCookieJar(){
        return new CookieJar() {

            @Override
            public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                Collection<Cookie> cookies = list;
                List<Cookie> collect = cookies.stream().collect(Collectors.toList());
                String host = ".nike.com";
                List<Cookie> cookiesList = cookiesMap.get(host);
                if (cookiesList != null){
                    //cookiesMap.remove(host);
                    cookiesList.addAll(collect);
                    cookiesMap.put(host,cookiesList);
                }else{
                    cookiesMap.put(host,collect);
                }
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                List<Cookie> cookiesList = cookiesMap.get(".nike.com");
                //if(null!=cookiesList)
//                cookiesList.stream().forEach(cookie -> {
//                    System.out.println(httpUrl.scheme()+"::"+cookie.name()+"::::::"+cookie.value());
//                });
                return cookiesList != null ? cookiesList : new ArrayList<>();
            }
        };
    }


    public static String get(String url){
        Request request = new Request.Builder().url(url).get().build();
        String result = "";
        try {
            try (Response response = okHttpClient.newCall(request).execute()) {
                System.out.println(url+":响应状态码:"+response.code());
                result = response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String postForm(String url,HashMap<String,String> map){

        FormBody.Builder builder = new FormBody.Builder();
        map.forEach((k,v)->{
            builder.add(k,v);
        });
        FormBody body = builder.build();

        Request request = new Request.Builder().url(loginUrl).post(body).build();
        String result = null;
        try {
            try (Response response = okHttpClient.newCall(request).execute()) {
                System.out.println(response.code());
                result = response.body().string();

                if(response.code()==302){
                    String location = response.header("location");
                    result = get(location);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String getJson(String url){

        Request request = new Request.Builder().addHeader("Content-Type","application/json").url(url).get().build();
        String result = null;
        try {
            try (Response response = okHttpClient.newCall(request).execute()) {
                System.out.println(url+":响应状态码:"+response.code());
                result = response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String postJson(String url,JSONObject object){
        ObjectMapper mapper = new ObjectMapper();
        String bodyString = null;
        try {
            bodyString = mapper.writeValueAsString(object);
            System.out.println("格式化后的json为:"+bodyString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(JSON,bodyString);

        Request request = new Request.Builder().url(url)
                .addHeader("host","unite.nikecloud.com").addHeader("Content-Type","text/plain")
                .addHeader("Origin","https://s3.nikecdn.com").addHeader("Connection","keep-alive")
                .addHeader("Proxy-Connection","keep-alive").addHeader("Accept","*/*")
                .addHeader("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
                .addHeader("Accept-Language","en-us").addHeader("Accept-Encoding","gzip, deflate")
        .post(body).build();
        String result = null;
        try {
            try (Response response = okHttpClient.newCall(request).execute()) {
                System.out.println(url+":响应状态码:"+response.code());
                result = response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }




    //https://github.com/zweed4u/SNKRS/blob/master/snkrs.py
    public static void main(String[] args) {
        String firstScreen = "https://www.nike.com/cn/zh_cn/c/nike-plus/snkrs-app";
        String loginUrl = "https://unite.nike.com/login?appVersion=397&experienceVersion=328&uxid=com.nike.commerce.nikedotcom.web&locale=zh_CN&backendEnvironment=identity&browser=Google%20Inc.&os=undefined&mobile=false&native=false&visit=1&visitor=75c6c916-48e3-4f24-ba7b-74aa8e2ff39e";

        String firstScreenResult = get(firstScreen);
        System.out.println("首屏结果:"+firstScreenResult);


        String init = "https://unite.nike.com/appInitialization?appVersion=397&experienceVersion=328&uxid=com.nike.commerce.nikedotcom.web&locale=zh_CN&backendEnvironment=identity&browser=Google%20Inc.&os=undefined&mobile=false&native=false&visit=&visitor=&clientId=HlHa2Cje3ctlaOqnxvgZXNaAs7T9nAuH&status=success&uxId=com.nike.commerce.nikedotcom.web&isAndroid=false&isIOS=false&isMobile=false&isNative=false&timeElapsed=861";
        String appInit = getJson(init);
        System.out.println("app Init 结果:"+appInit);


        JSONObject object = new JSONObject();
        object.put("username","jackjboss@163.com");
        object.put("password","JACKjboss123");
        object.put("client_id","HlHa2Cje3ctlaOqnxvgZXNaAs7T9nAuH");
        object.put("ux_id","com.nike.commerce.snkrs.ios");
        object.put("grant_type","password");
        String loginResult = postJson(loginUrl,object);
        System.out.println("登录结果:"+loginResult);


    }


}
