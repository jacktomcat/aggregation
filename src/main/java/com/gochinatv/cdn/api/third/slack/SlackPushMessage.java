package com.gochinatv.cdn.api.third.slack;

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


public class SlackPushMessage {
   
	public static final String APPLICATION_JSON = "application/json";
	public static final int TIME_OUT = 30000;
	
	public static void main(String[] args) throws Exception {
		JSONObject object = new JSONObject();
		object.put("text", "This is a line of text in a channel.\nAnd this is another line of text<https://www.baidu.com|https://www.baidu.com>.");
		String result = postJson("https://hooks.slack.com/services/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",object.toJSONString());
		System.out.println(result);
	}

	public static String postJson(String url, String data) throws Exception {

		BasicHttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setSoTimeout(httpParams, TIME_OUT);

		HttpClient client = new DefaultHttpClient(httpParams);
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, TIME_OUT);// 请求超时
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, TIME_OUT);// 读取超时

		HttpPost post = new HttpPost(url);

		StringEntity entity = new StringEntity(data, "utf-8");// 解决中文乱码问题
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
