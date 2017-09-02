package com.gochinatv.cdn.api.guava;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;


/**
 * guava test transform  partition
 * @author jacktomcat
 *
 */
public class ListTest {
   
	public static void main(String[] args) {
		List<String> list = Lists.newArrayList();
		list.add("spring");
		list.add("hibernate");
		list.add("structs");
		List<String> transform = Lists.transform(list,new Function<String,String>() {

			@Override
			public String apply(String input) {
				
				return input+" hello";
			}
			
		});
		transform.forEach(System.out::println);
		
		//partition 分区
		List<List<String>> partition = Lists.partition(transform, 3);
	}
}
