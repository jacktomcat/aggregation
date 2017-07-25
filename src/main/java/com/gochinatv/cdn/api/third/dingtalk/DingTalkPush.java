package com.gochinatv.cdn.api.third.dingtalk;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSONObject;


/**
 * 
 * https://open-doc.dingtalk.com/doc2/detail?treeId=172&articleId=104981&docType=1
 * 
 * @author tingyun
 *
 */
public class DingTalkPush {
	
	public static final int TIME_OUT = 10000;
	
	public static void main(String[] args) throws Exception {
		/*String access_token = getAccessToken();
		DingTalkClient client = new DefaultDingTalkClient("https://eco.taobao.com/router/rest");
		CorpMessageCorpconversationAsyncsendRequest req = new CorpMessageCorpconversationAsyncsendRequest();
		req.setMsgtype("oa");
		req.setAgentId(1234L);
		req.setUseridList("zhangsan,lisi");
		req.setDeptIdList("123,456");
		req.setToAllUser(false);
		req.setMsgcontentString("{\"message_url\": \"http://dingtalk.com\",\"head\": {\"bgcolor\": \"FFBBBBBB\",\"text\": \"头部标题\"},\"body\": {\"title\": \"正文标题\",\"form\": [{\"key\": \"姓名:\",\"value\": \"张三\"},{\"key\": \"爱好:\",\"value\": \"打球、听音乐\"}],\"rich\": {\"num\": \"15.6\",\"unit\": \"元\"},\"content\": \"大段文本大段文本大段文本大段文本大段文本大段文本大段文本大段文本大段文本大段文本大段文本大段文本\",\"image\": \"@lADOADmaWMzazQKA\",\"file_count\": \"3\",\"author\": \"李四 \"}}");
		CorpMessageCorpconversationAsyncsendResponse rsp = client.execute(req, access_token);
		
		System.out.println(rsp.getBody());
		*/
		pushMessage();
	}
	
	
	public static void pushMessage() throws Exception{
		for(int i=0;i<1;i++){
			JSONObject object = new JSONObject();
			object.put("msgtype", "text");
			object.put("text", "{\"content\":\"应用TEST-0626 响应时间持续超过严重阈值34."+i+"毫秒,请尽快处理!\"}");
			object.put("at", "{\"atMobiles\":[\"\"],\"isAtAll\":true}");
			String result = postJson("https://oapi.dingtalk.com/robot/send?access_token=f82b972689ee0ee26e83a05d082fb6c90676cf071802d33cc02ebf2d79b640f5",object);
			System.out.println(result);
			
			
			/*JSONObject object1 = new JSONObject();
			object1.put("msgtype", "link");
			object1.put("link", "{\"text\":\"应用TEST-0626 响应时间持续超过严重阈值34."+i+"毫秒,请尽快处理!\",\"title\":\"应用警告\",\"picUrl\":\"http://saas.tingyun.com/static/website/images/ty-logo.png\",\"messageUrl\":\"https://report.tingyun.com/browser/browser/application/7263/alarm?pageForm=alarmemail&eventId=206136&targetTypeId=53\"}");
			String result1 = postJson("https://oapi.dingtalk.com/robot/send?access_token=f82b972689ee0ee26e83a05d082fb6c90676cf071802d33cc02ebf2d79b640f5",object1);
			System.out.println(result1);
			
			//{"errmsg":"param error","errcode":300001}
			 * {"errmsg":"message msgtype not support","errcode":300001}
			 * {"errmsg":"请求的URI地址不存在","errcode":404}
			 * {"errmsg":"token is not exist","errcode":300001}
			 * {"errmsg":"ok","errcode":0}
			JSONObject object2 = new JSONObject();
			object2.put("msgtype", "actionCard");
			JSONObject object21 = new JSONObject();
			object2.put("actionCard", object21);
			object21.put("title", "应用警告");
			object21.put("text", "![logo](http://saas.tingyun.com/static/website/images/ty-logo.png)应用TEST-0626 响应时间持续超过严重阈值34."+i+"毫秒,请尽快处理!");
			object21.put("hideAvatar", "0");
			object21.put("btnOrientation", "0");
			object21.put("singleTitle", "查看");
			object21.put("singleURL", "https://report.tingyun.com/browser/browser/application/7263/alarm?pageForm=alarmemail&eventId=206136&targetTypeId=53");
			String result2 = postJson("https://oapi.dingtalk.com/robot/send?access_token=f82b972689ee0ee26e83a05d082fb6c90676cf071802d33cc02ebf2d79b640f5",object2);
			System.out.println(result2);*/
		}
	}
	
	public static String getAgentId() throws Exception {
		//https://oapi.dingtalk.com/microapp/visible_scopes?access_token=ACCESS_TOKEN
		return null;
	}
	
	
	/**
	 * 7200秒有效
	 * 1500次/min
	 * @throws Exception 
	 */
	public static String getAccessToken() throws Exception{
		String url = "https://oapi.dingtalk.com/gettoken?corpid=ding551af4a26915d79435c2f4657eb6378f&corpsecret=NxWeft1znHiGA9LcrXbjP4M8KJG1hR6t93G2G-VHJdjTaRj7iKQtG4R6D7GEXnzU";
		String result = getJson(url);
		/**
		 * response
		 * {
		 *	    "errcode": 0,
	     *	    "errmsg": "ok",
		 *	    "access_token": "fw8ef8we8f76e6f7s8df8s"
		 *	}
		 */
		JSONObject object = JSONObject.parseObject(result);
		String token = object.getString("access_token");
		System.out.println(token);
		return token;
	}
	
	
	public static String getJson(String url) throws Exception {

		BasicHttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setSoTimeout(httpParams, TIME_OUT);

		HttpClient client = new DefaultHttpClient(httpParams);
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, TIME_OUT);// 请求超时
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, TIME_OUT);// 读取超时

		HttpGet get = new HttpGet(url);
		HttpResponse response = client.execute(get);
		
		HttpEntity result = response.getEntity();
		if (result != null) {
			return EntityUtils.toString(result, "UTF-8").trim();
		}

		return null;
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
