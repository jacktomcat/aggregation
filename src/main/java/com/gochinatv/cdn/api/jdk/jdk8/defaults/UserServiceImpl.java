package com.gochinatv.cdn.api.jdk.jdk8.defaults;



/**
 * 测试java8的接口中的默认方法default
 * @author jacktomcat
 *
 */
public class UserServiceImpl implements UserService{

	@Override
	public String getName(String name) {
		return name;
	}

	@Override
	public String getInfo(String name) {
		if(name==null){
			return "11";
		}
		return UserService.super.getInfo(name);
	}
	
	public static void main(String[] args) {
		UserServiceImpl usi = new UserServiceImpl();
		System.out.println(usi.getName("zhu"));
		
		System.out.println(usi.getInfo(null));
		
		System.out.println(usi.getInfo("jack"));
	}

}
