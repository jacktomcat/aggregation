package com.gochinatv.cdn.api.guava;

import java.util.Set;

import org.junit.Test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;

/**
 * 
 * @author jacktomcat
 *
 */
public class MultiCollectionTest {

	@Test
	public void multiMap(){
		Multimap<String, String> map = HashMultimap.create();
		map.put("tom","高等数学");
		map.put("tom","高等数学");
		map.put("jack","线性代数");
		map.put("lucy","数学分析");
		map.put("tom","概率论");
		
		map.get("tom").forEach(System.out::println);
		map.asMap().forEach((k,v)->{
			System.out.println(k+" " + v);
			/*v.forEach(val->{
				System.out.println(k+" " + val);
			});*/
		});
	}
	
	
	@Test
	public void multiSet(){
		String str = "this is a cat and this is a mice where is the food";

        //将句子按照空格划分为单词并存在字符串数组中
        String[] strArray = str.split(" ");

        //创建一个Multiset对象，注意接口不可new一个对象
        Multiset<String> set=HashMultiset.create();
        for(String s:strArray){
            set.add(s);
        }
        
        set.forEach(key->{
        	System.out.println("key:"+key+",count:"+set.count(key));
        });
        
        System.out.println("=============以下方式也可以实现=================");
        
        //获取集合中元素，保存在set中，方便便利
        Set<String> letters=set.elementSet();
        for(String temp:letters){
            System.out.println(temp+"  "+set.count(temp));
        }
	}
	
	
	//http://www.cnblogs.com/peida/p/3180129.html
	//这里和 HashMultimap区别是,键值都是可以重复的
	@Test
	public void arrayListMultimap(){
		ArrayListMultimap<String, String> map = ArrayListMultimap.create();
		map.put("tom","高等数学");
		map.put("tom","高等数学");
		map.put("jack","线性代数");
		map.put("lucy","数学分析");
		map.put("tom","概率论");
		
		map.keySet().forEach(k->{
			System.out.println(k+" "+map.get(k));
		});
		
	}
	
	//双向Map(Bidirection Map) 键与值都不能重复（unique value map） ,这里biMap可以反转
	@Test
	public void biMap(){
		BiMap<String, String> map = HashBiMap.create();
		map.put("tom","高等数学");
		map.put("tom","离散数学");
		map.put("jack","线性代数");
		map.put("lucy","概率论1");
		map.put("tom","概率论");
		map.forcePut("like", "线性代数");//如果已经存在有key和value相同的一个元素,则删除之后强制放进去一个,
		
		map.forEach((k,v)->{
			System.out.println(k+" "+v);
		});
		
		System.out.println("===反转之后==");
		map.inverse().forEach((k,v)->{
			System.out.println(k+" "+v);
		});
	}
	
	
}
