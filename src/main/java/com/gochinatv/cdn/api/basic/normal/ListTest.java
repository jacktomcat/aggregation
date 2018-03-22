package com.gochinatv.cdn.api.basic.normal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.google.common.collect.Lists;


/**
 * Created by jacktomcat on 17/8/28.
 */
public class ListTest {


    public static void main(String[] args) {
    	List<User> userList = new ArrayList<>();
    	User user1 = new User(1, "aa");
    	User user2 = new User(-1, "bbb");
    	User user3 = new User(3, "ccc");
    	user1.setVideos(Lists.newArrayList("user1","user11"));
    	user2.setVideos(Lists.newArrayList("user2","user22"));
    	user3.setVideos(Lists.newArrayList("user3","user33"));
    	userList.add(user1);
    	userList.add(user2);
    	userList.add(user3);
    	List<User> collect = userList.stream().filter(u->u.getId()!=-1).collect(Collectors.toList());
    	System.out.println(collect);
    	
    	User user = userList.stream().filter(u->u.getId()!=-1).min((a,b)->a.getId()-b.getId()).get();
    	System.out.println(user);
    	
    	
    	/**
    	 * https://segmentfault.com/q/1010000006895030 
    	 * 阐述了 map和flatMap的区别
    	 */
    	List<String> collect1 = userList.stream().map(u->u.getName()).collect(Collectors.toList());
    	
    	List<String> collect2 = userList.stream().flatMap(u->u.getVideos().stream()).collect(Collectors.toList());
    	System.out.println(collect2);
    }


}



class User{
	
	int id;
	String name;
	List<String> videos;
	
	public User(int id,String name){
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<String> getVideos() {
		return videos;
	}

	public void setVideos(List<String> videos) {
		this.videos = videos;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	
}
