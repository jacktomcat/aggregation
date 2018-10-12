package com.gochinatv.cdn.api.ognl;

import com.google.common.collect.Lists;
import ognl.Ognl;
import ognl.OgnlException;


/**
 * http://java12345678.iteye.com/blog/2030569
 */
public class CallMethodTest {


    public static void main(String[] args) throws OgnlException {
        Company company = new Company();
        company.setEmployeeNum(10);

        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("张三");
        company.setEmployeeList(Lists.newArrayList(employee));

        int num = (Integer)Ognl.getValue("employeeNum",company);
        int empSize = (Integer)Ognl.getValue("employeeList.size()",company);
        System.out.println("删除前list size()->"+empSize);

        Ognl.getValue("employeeList.remove(0)", company);//调用list.remove方法
        empSize = (Integer)Ognl.getValue("employeeList.size()",company);

        System.out.println(num);
        System.out.println("删除后list size()->"+empSize);
    }
}
