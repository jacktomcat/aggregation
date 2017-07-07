package com.gochinatv.cdn.api.third.pagerduty;

import java.io.IOException;

import com.google.common.collect.ImmutableMap;
import com.squareup.pagerduty.incidents.NotifyResult;
import com.squareup.pagerduty.incidents.PagerDuty;
import com.squareup.pagerduty.incidents.Resolution;
import com.squareup.pagerduty.incidents.Trigger;


/**
 * https://v2.developer.pagerduty.com/v2/page/api-reference#!/Incidents/get_incidents
 * @author jack
 *
 */
public class PagerDutyPushMessage {

	public static void main(String[] args) throws Exception {
		trigger();
		//relieve();
	}
	
	
	public static void trigger(){
		PagerDuty pagerDuty = PagerDuty.create("65744fde143e406b9dc46ca363141563");
		Trigger trigger = new Trigger.Builder("告警:「测试-阈值0503」HTTP错误率超过阈值").withIncidentKey("APP-1298")
				.addDetails("App名称", "测试-阈值0503")
				.addDetails(ImmutableMap.of(
							"告警内容", "HTTP错误率超过阈值", 
							"HTTP错误率（阈值）", "19.4 % （5 %）",
							"告警开始时间","2017-07-06 16:07",
							"告警持续时间","超过2分钟"))
				.build();
		try {
			NotifyResult notify = pagerDuty.notify(trigger);
			//notify.message();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void relieve(){
		PagerDuty pagerDuty = PagerDuty.create("65744fde143e406b9dc46ca363141563");
		Resolution resolution = new Resolution.Builder("APP-1298")
			    .withDescription("该警报已经解决")
			    .addDetails("告警内容", "HTTP错误率超过阈值")
			    .addDetails(ImmutableMap.of(
						"告警内容", "HTTP错误率超过阈值", 
						"HTTP错误率（阈值）", "19.4 % （5 %）",
						"告警开始时间","2017-07-06 16:07",
						"告警持续时间","超过2分钟"))
			    .build();
			try {
				pagerDuty.notify(resolution);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	
	
}
