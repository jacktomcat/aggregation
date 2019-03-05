package com.gochinatv.cdn.api.okhttp;

import com.google.common.base.Preconditions;
import okhttp3.OkHttpClient;
import org.apache.commons.collections.CollectionUtils;

import java.util.concurrent.TimeUnit;

/**
 * ${DESCRIPTION}
 *
 * @auhtor jacktomcat
 * @create 2019-01-08 下午10:30
 */
public final class HealthCheck {

    private static OkHttpClient okHttpClient;

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

    /**
     * 健康检查
     */
    public static void check(Resource resource){
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(resource.getMachines()),"调用机器不能为空");
        String strategy = resource.getStrategy();
        int size = resource.getMachines().size();


    }






}
