package com.gochinatv.cdn.api.guava;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

/**
 * 
 * @author jacktomcat
 *
 */
public class CollectionsTest {
   
	 @Test
	 public void collectionsTest() {
		 List<String> data = new ArrayList<>();
		 data.add("ab");
		 data.add("bc");
		 data.add("df");
		 data.add("tyh");
		 data.add("love");
		 
		 Collection<String> result = Collections2.filter(data,new Predicate<String>() {
			@Override
			public boolean apply(String input) {
				return !input.startsWith("a");
			}
		 });
		 result.forEach(System.out::println);
		 
		 
		 /**
		  * 排列组合
		  */
		 /*Collection<List<String>> orderedPermutations = Collections2.orderedPermutations(data);
		 System.out.println(orderedPermutations.size());
		 orderedPermutations.forEach(c->{
			 c.forEach(v->System.out.println(v));
		 });*/
		 
	 }
}
