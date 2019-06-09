package com.gochinatv.cdn.api.alarm;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.ToString;
import okhttp3.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Alarm {


    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static ExecutorService executorService =  Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws Exception {
        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS).build();


        String strMobile = "13716472560"; //tel 的 mobile 字段的内容
        String strAppKey = "dddddddddddddddddD"; //sdkappid 对应的 appkey，需要业务方高度保密
        String strRand = RandomStringUtils.randomNumeric(10); //URL 中的 random 字段的值
        long time = System.currentTimeMillis()/1000;
        String strTime = time+""; //UNIX 时间戳
        String sig = DigestUtils.sha256Hex("appkey="+strAppKey+"&random="+strRand+"&time="+strTime+"&mobile="+strMobile);

        System.out.println("sig="+sig);

        String postUrl = "https://cloud.tim.qq.com/v5/tlsvoicesvr/sendvoiceprompt?sdkappid=33333333&random="+strRand;

        JSONObject content = new JSONObject();
        JSONObject telSub = new JSONObject();
        content.put("ext","");
        content.put("playtimes",2);
        content.put("promptfile","触发新的告警信息");
        content.put("prompttype",2);
        content.put("sig",sig);
        telSub.put("mobile",strMobile);
        telSub.put("nationcode","86");
        content.put("tel",telSub);
        content.put("time",time);
        String postContent = content.toJSONString();
        System.out.println(""+postContent);
        System.out.println("postUrl="+postUrl);

        RequestBody body = RequestBody.create(JSON,postContent);
        Request request = new Request.Builder().post(body).url(postUrl).build();
        Response response = okHttpClient.newCall(request).execute();
        String result = response.body().string();

        //错误码，0表示成功（计费依据），非0表示失败
        /**
         * {
         *     "result": 0,
         *     "errmsg": "OK",
         *     "callid": "xxxx",
         *     "ext": ""
         * }
         */
        System.out.println(result);


        //executorService.submit(new CallWorkTask(null));


    }


    public static class CallWorkTask implements Runnable {

        private CallTask task;

        public CallWorkTask(CallTask task) {
            this.task = task;
        }

        @Override
        public void run() {
            CallItem item = task.getItems().stream().findFirst().get();
            if (task.getCurrentCallCount()>=task.getCallMaxCount()) {//超过最大的发送次数
                return;
            }
            if(item.isCalled()) {//累计轮询次数
                task.setCurrentCallCount(task.getCurrentCallCount()+1);
            }
            item.setCalled(true);
            item.setCalledTime(new Date());
            //Response respone = 执行发送(item);
            //item.setCallid(response.callid);
            //item.setErrmsg(response.errmsg);
            //item.setCallSuccess(response.result==0);

            if (item.isCallSuccess()){
                return;
            }
            task.getItems().remove(item);
            task.getItems().add(item);
            //失败之后接着执行新的任务
            //再次调用
        }
    }


    @Data
    @ToString
    public static class CallTask {
        private List<CallItem> items;
        private int callMaxCount;
        private int currentCallCount;
        private String eventId;
    }


    @Data
    @ToString
    public static class CallItem {

        private String mobile;
        private boolean callSuccess = false;
        private boolean called = false;
        private String errmsg;
        private String callid;
        private Date calledTime;
    }


}
