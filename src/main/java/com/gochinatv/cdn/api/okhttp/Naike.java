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


/**
 * ${DESCRIPTION}
 *
 * @auhtor zhuhuihui
 * @create 2018-03-28 下午10:39
 */
public class Naike {


    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    static OkHttpClient okHttpClient = new OkHttpClient()
            .newBuilder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS)
            .connectTimeout(30,TimeUnit.SECONDS).cookieJar(setCookieJar())
            .sslSocketFactory(createSSLSocketFactory())
            .hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            })
            .build();


    public static void getFirstScreen(){

        String url = "https://unite.nike.com/appInitialization?appVersion=394&experienceVersion=328&uxid=com.nike.commerce.snkrs.ios&locale=zh_CN&backendEnvironment=identity&browser=Apple%20Computer%2C%20Inc.&os=undefined&mobile=true&native=true&visit=&visitor=&clientId=G64vA0b95ZruUtGk1K0FkAgaO3Ch30sj&status=success&uxId=com.nike.commerce.snkrs.ios&isAndroid=false&isIOS=true&isMobile=true&isNative=true&timeElapsed=132";
        //String url = "https://s3.nikecdn.com/unite/mobile.html?mid=38714843858907497782150520775285411504?iOSSDKVersion=2.8.4&clientId=G64vA0b95ZruUtGk1K0FkAgaO3Ch30sj&uxId=com.nike.commerce.snkrs.ios&view=none&locale=zh_CN&backendEnvironment=identity";

        Request request = new Request.Builder().url(url).get().build();
        try {
            try (Response response = okHttpClient.newCall(request).execute()) {
                System.out.println(response.code());
                System.out.println(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static CookieJar setCookieJar(){
        return new CookieJar() {

            private final Map<String, List<Cookie>> cookiesMap = new HashMap<String, List<Cookie>>();

            @Override
            public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                Collection<Cookie> cookies = list;
                List<Cookie> collect = cookies.stream().collect(Collectors.toList());
                String host = ".nike.com";
                List<Cookie> cookiesList = cookiesMap.get(host);
                if (cookiesList != null){
                    //cookiesMap.remove(host);
                    cookiesList.addAll(collect);
                }
                cookiesMap.put(host,collect);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                List<Cookie> cookiesList = cookiesMap.get(".nike.com");
                if(null!=cookiesList)
                cookiesList.stream().forEach(cookie -> {
                    System.out.println(httpUrl.scheme()+"::"+cookie.name()+"::::::"+cookie.value());
                });
                return cookiesList != null ? cookiesList : new ArrayList<>();
            }
        };
    }


    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }



    //https://github.com/zweed4u/SNKRS/blob/master/snkrs.py
    public static void login(){
       String url="https://s3.nikecdn.com/login?appVersion=394&experienceVersion=328&uxid=com.nike.commerce.snkrs.ios&locale=zh_CN&backendEnvironment=identity&browser=Apple%20Computer%2C%20Inc.&os=undefined&mobile=true&native=true&visit=1&visitor=6cf0a068-e4d6-43b8-8ae0-9cf3d932971b";

        //String url="https://s3.nikecdn.com/login?appVersion=393&experienceVersion=327&uxid=com.nike.commerce.snkrs.ios&locale=zh_CN&backendEnvironment=identity&browser=Apple%20Computer%2C%20Inc.&os=undefined&mobile=true&native=true&visit=1&visitor=3f628cc4-a34b-47e7-af36-480b2f544c59";
//        FormBody body = new FormBody.Builder().add("username","weixuerui920@163.com").
//                add("password","768321920Xue")
//                .add("client_id","G64vA0b95ZruUtGk1K0FkAgaO3Ch30sj")
//                .add("ux_id","com.nike.commerce.snkrs.ios")
//                .add("grant_type","password")
//                .build();

        JSONObject object = new JSONObject(true);
        object.put("username","jackjboss@163.com");
        object.put("password","JACKjboss123");
        object.put("client_id","G64vA0b95ZruUtGk1K0FkAgaO3Ch30sj");
        object.put("ux_id","com.nike.commerce.snkrs.ios");
        object.put("grant_type","password");

        ObjectMapper mapper = new ObjectMapper();
        String bodyString = null;
        try {
            bodyString = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //System.out.println(bodyString);
        RequestBody body = RequestBody.create(JSON,bodyString);

        Request request = new Request.Builder().url(url)
//                .addHeader("X-NewRelic-ID","VQYGVF5SCBADUVBRBgAGVg==")
//                .addHeader("Host","s3.nikecdn.com")
//                .addHeader("Accept","*/*")
//                .addHeader("Accept-Encoding","gzip, deflate")
//                .addHeader("Accept-Language","en-us")
//                .addHeader("Content-Type","text/plain")
//                .addHeader("Referer","https://s3.nikecdn.com/unite/mobile.html?mid=57110767586367889216023751611428736603?iOSSDKVersion=2.8.4&clientId=G64vA0b95ZruUtGk1K0FkAgaO3Ch30sj&uxId=com.nike.commerce.snkrs.ios&view=none&locale=zh_CN&backendEnvironment=identity")
//
//
//                .addHeader("Origin","https://s3.nikecdn.com")
//                .addHeader("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 7_1_2 like Mac OS X) AppleWebKit/537.51.2 (KHTML, like Gecko) Mobile/11D257")
//                .addHeader("Connection","keep-alive")
                //.addHeader("Cookie","s_pers=%20s_fid%3D599A6B0D38E2D4FD-2C97306DD00A9B3F%7C1680018367819%3B%20c5%3Dunified%2520profile%253Elogin%7C1522253767820%3B%20c6%3Dunified%2520profile%7C1522253767821%3B; s_sess=%20c51%3Dvertical%3B%20s_cc%3Dtrue%3B; neo.experiments=%7B%22unite-native%22%3A%7B%7D%7D; _abck=16718CD5C89DA70290E26012B8E298A1B832579AD14200000CB8BB5A0A69AE40~0~xKwXHN8Qp/91xnh5ZXntbUgSXNwO2M/CgBZp62k7lTc=~-1~-1; ak_bmsc=C1B2A237D012F4B675BA92804D4A5D1EB832579AD14200003CB8BB5A234EA432~plqEvf16xWAm5ZzkIhreYGr3kRNr+OYqMNQKrVe/ETW2At0kRavpVQfdZEsO9YhJIYrmZnqKPTBhazJUlExG8rF05SxW3e0cmY+INzgYJM7fs5rdFqXyO+Nuf+SQR8RSjSkiETrU2OpcTytSjMG7PICuJOvNLlRZUSFubznHqN0DgGIF933jx2QvAbz+tBwa0iI4Kxr/k68rL214xczHicNh0/TaNakuI+10kQGORSkqk=; bm_sz=98F8E99D92A189C7775CF93BDC3E784C~QAAQmlcyuIKOn2tiAQAADu9GbQvaMAf51ZoSToblBlq7XY+LGTq0jpNj+z20/1bPRpePv4jOMcPmDA4timxXS1drkMbgp5SHeJASYHv2YzrPf8H+HX9x3VWr12YU68mAEalvgG+TMQENg2/uGoJh5k98iTezyIDSF4W2KLfr+nY85gz21/RKshIYiZevQFU=; guidS=376af5b3-e2a3-436e-8b6c-675bf756b60d; guidU=b0b36a92-f719-4408-eff0-91e440dabf14; neo.swimlane=90")

                .post(body).build();
        try {
            try (Response response = okHttpClient.newCall(request).execute()) {
                System.out.println("登录");
                System.out.println(response.code());
                System.out.println(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        getFirstScreen();
        login();
    }


}
