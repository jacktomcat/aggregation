package com.gochinatv.cdn.api.ognl;

import lombok.Data;

import java.util.List;

public class Company {

    private int employeeNum;
    List<Employee> employeeList;
    
    
	public int getEmployeeNum() {
		return employeeNum;
	}
	public void setEmployeeNum(int employeeNum) {
		this.employeeNum = employeeNum;
	}
	public List<Employee> getEmployeeList() {
		return employeeList;
	}
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

}
