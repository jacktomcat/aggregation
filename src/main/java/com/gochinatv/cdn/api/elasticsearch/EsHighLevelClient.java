package com.gochinatv.cdn.api.elasticsearch;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.Arrays;

/**
 * ${DESCRIPTION}
 * ES 高级别的api
 * @auhtor jacktomcat
 * @create 2019-01-25 下午9:38
 */
public class EsHighLevelClient {

    public static String esUrl = "http://192.168.2.151:9200";

    public static void main(String[] args) {

        try {
            RestHighLevelClient client = initElasticSearchClient();
            client.search(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static RestHighLevelClient initElasticSearchClient() {
        /*HttpHost[] httpHosts = Arrays.stream(esUrl.split(",")).map(HttpHost::create).toArray(HttpHost[]::new);
        return new RestHighLevelClient(RestClient.builder(httpHosts).setRequestConfigCallback(
                requestConfigBuilder -> requestConfigBuilder.setConnectTimeout(1300).setSocketTimeout(100).setConnectionRequestTimeout(200)
        ).setMaxRetryTimeoutMillis(10000));*/
        return null;
    }
}
