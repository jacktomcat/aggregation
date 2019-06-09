package com.gochinatv.cdn.api.guava;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Range;

public class RangeUtils {
    
	public static void main(String[] args) {
		Map<Byte, byte[]> data = Maps.newHashMap();
		Byte b=12;
		byte[] bt = data.getOrDefault(b, new byte[]{1,2,3});
		System.out.println(bt[0]);
		
		List<Object> list = Lists.newArrayList();
		BlockingQueue<Object> queue = null;
		StopWatch stopwatch = StopWatch.createStarted();
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long time = stopwatch.getTime(TimeUnit.MILLISECONDS);
		System.out.println(time);

		list.forEach(obj -> {
			System.out.println(obj.toString()); 
		});

		org.apache.commons.lang3.Range<Integer> between = org.apache.commons.lang3.Range.between(1, 4);
		/*Range<Integer> closed = Range.closedOpen(1, 4);
		System.out.println("====="+closed.lowerBoundType()+"===="+closed.upperBoundType());
		System.out.println("====="+closed.lowerEndpoint()+"===="+closed.upperEndpoint());*/


	}
}
