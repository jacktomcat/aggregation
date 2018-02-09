package com.gochinatv.cdn.api.jdk.jdk8.lambda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Supplier 使用
 * @author tingyun
 *
 */
public class SupplierTest {

	
	public static void main(String[] args) {
		
		getList(SupplierTest::params);
	}
	
	public static void getList(DataSupplier supplier){
		List<String> output = supplier.getData("test:5");
		output.forEach(v->{
			System.out.println(v);
		});
	}
	
	
	public static List<String> params(String key){
		Map<String, List<String>> paramsMap = new HashMap<>(); 
		
		for(int i=0;i<10;i++){
			List<String> data = new ArrayList<>(10);
			paramsMap.putIfAbsent("test:"+i, data);
			for(int j=0;j<10;j++){
				data.add("test:"+i+",value:"+j);
			}
		}
		return paramsMap.get(key);
	}
}
