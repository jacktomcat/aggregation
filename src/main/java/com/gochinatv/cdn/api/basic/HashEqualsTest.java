package com.gochinatv.cdn.api.basic;

import java.util.HashMap;


/**
 * http://www.cnblogs.com/jzb-blog/p/6637823.html
 * @author jacktomcat
 * 
 * 扩容机制:
 *       size     threshold    loadFactor  
 * 		  16        12            0.75          
 *        32        24            0.75
 * 		  64        48            0.75
 *  	 128        96            0.75
 *       256        192           0.75
 * 初始容量为16的时候,实际存储值为12,  16*0.75=12 ,当存储size超过12的时候需要扩容为 12*2=24,那么size也需要扩容为16*2=32,比如当你存放13个对象的时候,那么map的容量为32,threashold为24,但是实际的size为13
 * 
 */
public class HashEqualsTest {
  
	
	public static void main(String[] args) {
		HashMap<User, User> dataMap = new HashMap<>();
		User user1 = new User(1, "张三");
		User user2 = new User(1, "张三");
		
		dataMap.put(user1, user1);
		dataMap.put(user2, user2);
		
		for(int i=0;i<13;i++){
			dataMap.put(new User(i, "李四"), new User(i, "李四"));
		}
		
		System.out.println((int)Math.min(10 * 0.75, 100 + 1));
		
		//如果只重写 equals那么equals 比较为true, 下面的hashcode是不相等的
		System.out.println("比较user1和user2 "+user1.equals(user2));
		
		//如果只重写 equals那么equals 比较为true, hashcode是不相等的,如果重写equals和hashcode这里是相等的
		System.out.println("比较user1 "+user1.hashCode() + ",比较user2 "+user2.hashCode());
		
		System.out.println(dataMap.size());
	}
	
}

class User{
	private int id;
	private String name;
	
	public User(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
