package com.gochinatv.cdn.api.okhttp;

import lombok.Data;

/**
 * ${DESCRIPTION}
 *
 * @auhtor jacktomcat
 * @create 2019-01-29 下午8:39
 */
@Data
public class HttpHosts {

    private String url;

    private String strategy; // random,weight  (随机和权重1:1)

}
