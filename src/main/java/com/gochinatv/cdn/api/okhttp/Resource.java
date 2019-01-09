package com.gochinatv.cdn.api.okhttp;

import lombok.Data;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @auhtor jacktomcat
 * @create 2019-01-08 下午10:34
 */

@Data
public class Resource {

    private String strategy;//  random,weight

    private List<Machine> machines; //机器

}
