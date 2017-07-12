package com.gochinatv.cdn.api.serialization;

import java.io.Serializable;

public class Users implements Serializable {

	private int id;

	private String userName;

	private int age;

	private String phone;
	
	private String address;

	public Users() {
	}

	public Users(int id, String userName, int age, String phone,String address) {
		this.id = id;
		this.userName = userName;
		this.age = age;
		this.phone = phone;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
