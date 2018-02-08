package com.gochinatv.cdn.api.jdk.jdk8.lambda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupplierTest {

	
	public static void main(String[] args) {
		
		//getList(SupplierTest::params);
	}
	
	public static void getList(DataSupplier supplier){
		List<String> output = supplier.getData("");
		output.forEach(v->{
			System.out.println(v);
		});
	}
	
	
	public static Map<String, String> params(String key){
		Map<String, List<String>> paramsMap = new HashMap<>(); 
		
		/*List<String,list> data = new ArrayList<>();
		data.add("test1");
		data.add("test2");
		data.add("test3");
		data.add("test4");*/
		return null;
	}
}
