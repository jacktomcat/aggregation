package com.gochinatv.cdn.api.ognl;

import ognl.Ognl;
import ognl.OgnlException;

/**
 * 逻辑表达式测试
 */
public class LogicExpressionTest {


    public static void main(String[] args) throws OgnlException {
        Employee employee = new Employee();
        employee.setId(100);
        employee.setAge(3);

        int value = (Integer)Ognl.getValue("(id/2)",employee);
        System.out.println(value);

        value = (Integer)Ognl.getValue("(id/2) > 20 ? 222 : 111",employee);
        System.out.println(value);

        value = (Integer)Ognl.getValue("(id+9)/(age*3)-2",employee);
        System.out.println(value);

        boolean v = (Boolean)Ognl.getValue("id > 20 && age >0", employee);
        System.out.println(v);

        v = (Boolean)Ognl.getValue("id > 100 || age <=0", employee);
        System.out.println(v);
    }
}
