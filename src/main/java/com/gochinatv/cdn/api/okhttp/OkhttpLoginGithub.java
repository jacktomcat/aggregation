package com.gochinatv.cdn.api.okhttp;

import okhttp3.*;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
 * ${DESCRIPTION}
 *
 * @auhtor zhuhuihui
 * @create 2018-03-28 下午10:39
 */
public class OkhttpLoginGithub {


    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private final static Map<String, List<Cookie>> cookiesMap = new HashMap<String, List<Cookie>>();


    static String url1 = "https://github.com/login";


    static String loginUrl = "https://github.com/session";


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


    public static String getFirstScreen(String url){

        Request request = new Request.Builder().url(url).get().build();
        String result = null;
        try {
            try (Response response = okHttpClient.newCall(request).execute()) {
                System.out.println(response.code());
                //System.out.println(response.body().string());
                result = response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static CookieJar setCookieJar(){
        return new CookieJar() {

            @Override
            public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                Collection<Cookie> cookies = list;
                List<Cookie> collect = cookies.stream().collect(Collectors.toList());
                String host = ".github.com";
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
                List<Cookie> cookiesList = cookiesMap.get(".github.com");
                //if(null!=cookiesList)
//                cookiesList.stream().forEach(cookie -> {
//                    System.out.println(httpUrl.scheme()+"::"+cookie.name()+"::::::"+cookie.value());
//                });
                return cookiesList != null ? cookiesList : new ArrayList<>();
            }
        };
    }


    //https://github.com/zweed4u/SNKRS/blob/master/snkrs.py
    public static String login(String authenticity_token){

        FormBody body = new FormBody.Builder().
        add("commit","Sign in")
        .add("utf8","✓")
        .add("authenticity_token",authenticity_token)
        .add("login","jackjboss@gmail.com")
                .add("password","xxx")
        .build();

        Request request = new Request.Builder().url(loginUrl).post(body).build();
        String result = null;
        try {
            try (Response response = okHttpClient.newCall(request).execute()) {
                System.out.println(response.code());
                result = response.body().string();

                if(response.code()==302){
                    String location = response.header("location");
                    result = getFirstScreen(location);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args) {

        String reuslt = getFirstScreen(url1);
        int index = reuslt.lastIndexOf("authenticity_token");
        String authenticity_token = reuslt.substring(index,index+120).replaceAll("authenticity_token","")
                .replaceAll(" ","").replaceAll("value=","").replaceAll("\"","").replaceAll("/>","");
        System.out.println("authenticity_token:"+authenticity_token);

        String login = login(authenticity_token);
        System.out.println(login);
    }


}
