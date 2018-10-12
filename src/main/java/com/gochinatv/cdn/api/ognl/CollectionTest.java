package com.gochinatv.cdn.api.ognl;

import ognl.Ognl;
import ognl.OgnlException;

import java.util.*;


/**
 * 集合表达式
 */
public class CollectionTest {


    public static void main(String[] args) throws OgnlException {

        List list = new ArrayList();
        Set set = new HashSet();
        Employee [] array = new Employee [10];

        for(int i=0;i<10;i++){
            Employee employee = new Employee();
            employee.setId(i+1);
            employee.setName("张三"+i);
            list.add(employee);
            set.add(employee);
            array[i]= employee;
        }
        HashMap root = new HashMap();
        root.put("list",list);
        root.put("set",set);
        root.put("array",array);

        Object obj = Ognl.getValue("list.{name}",root);//提取List中所有元素的某个公用属性值，而生成新的集合ArrayList
        System.out.println(obj); //[张三0, 张三1, 张三2, 张三3, 张三4, 张三5, 张三6, 张三7, 张三8, 张三9]

        obj = Ognl.getValue("set.{name}",root);//提取SET中所有元素的某个公用属性值，而生成新的集合ArrayList
        System.out.println(obj); //[张三3, 张三7, 张三0, 张三4, 张三8, 张三1, 张三5, 张三9, 张三2, 张三6]

        obj = Ognl.getValue("array.{name}",root);//提取数组中所有元素的某个公用属性值，而生成新的集合ArrayList
        System.out.println(obj);//[张三0, 张三1, 张三2, 张三3, 张三4, 张三5, 张三6, 张三7, 张三8, 张三9]

        int size = (Integer)Ognl.getValue("list.{? #this.id>5}.size()",root);//提取List中所有元素的 id>5 的所有元素，而生成新的集合ArrayList，再调用ArrayList.size
        System.out.println(size); // 5

        obj = Ognl.getValue("list.{^ #this.id>5}",root);
        System.out.println(obj); //匹配元素的第一个元素的集合  [Employee(id=6, name=张三5)]

        String name = (String)Ognl.getValue("list.{^ #this.id>5}[0].name",root);
        System.out.println(name); //张三5

        name = (String)Ognl.getValue("list.{$ #this.id>5}[0].name",root);//提取List中所有元素的 id>5 的最后一个元素生成ArrayList，再获取它中唯一元素的name属性
        System.out.println(name); //张三9
    }


}
