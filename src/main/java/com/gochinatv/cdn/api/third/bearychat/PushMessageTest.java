package com.gochinatv.cdn.api.third.bearychat;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class PushMessageTest {

	public static final String APPLICATION_JSON = "application/json";
	public static final int TIME_OUT = 10000;

	public static void main(String[] args) throws Exception {
		JSONObject data = new JSONObject();
		data.put("text", "你好,测试bearychat");
		data.put("markdown", "true");
		data.put("text", "你好,测试bearychat");
		
		postJson("https://hook.bearychat.com/=bwAYM/incoming/42cd77028c0a79596700ada76ec6e559",data);
		
	}
	
	
	public static String postJson(String url,JSONObject data) throws Exception {

		BasicHttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setSoTimeout(httpParams, TIME_OUT);

		HttpClient client = new DefaultHttpClient(httpParams);
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, TIME_OUT);// 请求超时
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, TIME_OUT);// 读取超时

		HttpPost post = new HttpPost(url);

		StringEntity entity = new StringEntity(data.toString(), "utf-8");// 解决中文乱码问题
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		post.setEntity(entity);
		HttpResponse response = client.execute(post);
		HttpEntity result = response.getEntity();
		if (result != null) {
			return EntityUtils.toString(result, "UTF-8").trim();
		}
		
		return null;
	}

}
