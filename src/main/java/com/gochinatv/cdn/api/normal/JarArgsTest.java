package com.gochinatv.cdn.api.normal;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class JarArgsTest {
   
	public static void main(String[] args) {
       //java -Dinterval=30 -Dname=zhangsan -Dtest=test -jar jarargsTest.jar
       String interval = System.getProperty("interval");
       String name = System.getProperty("name");
       String test = System.getProperty("test");
       //以上是获取自定义参数的
       System.out.println("interval:=="+interval+" name:=="+name+" test:=="+test);
       
       System.out.println("============prop=================");
       Properties properties = System.getProperties();
       Set<Entry<Object, Object>> entrySet = properties.entrySet();
       for (Iterator iterator = entrySet.iterator(); iterator.hasNext();) {
		Entry<Object, Object> entry = (Entry<Object, Object>) iterator.next();
		  System.out.println(entry.getKey()+"----------"+entry.getValue());
	   }
       System.out.println("===========env==================");
       Map<String, String> getenv = System.getenv();
       Set<Entry<String, String>> entrySet2 = getenv.entrySet();
       for (Iterator iterator = entrySet2.iterator(); iterator.hasNext();) {
   		Entry<Object, Object> entry = (Entry<Object, Object>) iterator.next();
   		  System.out.println(entry.getKey()+"----------"+entry.getValue());
   	   }
	}
}
