package com.gochinatv.cdn.api.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * transient 一般结合 Serializable 序列化和反序列化使用,声明为transient的变量 序列化之后不可见,获取的值全部类似于实例化一个类的初始值
 * transient 修饰的 static的变量没有任何作用,值不会改变
 * @author jacktomcat
 *
 */
public class TransientTest implements Serializable{

	private static final long serialVersionUID = -5836283489677344417L;
	
	
    private transient int classValue = 10;
    private transient Date date = new Date();
    private transient static int staticValue = 10;//transient 对static变量无效
    
    
    
    public static void main(String[] args) throws Exception {
    	TransientTest m = new TransientTest();
        m.classValue = 11;
        TransientTest.staticValue = 11;
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File("0xjh000")));
        out.writeObject(m);

        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(
                new File("0xjh000")));
        TransientTest m1 = (TransientTest) in.readObject();
        in.close();

        System.out.println(m1.classValue);//0
        System.out.println((m1.date == null ? "date is null" : "date is not null"));//date is null
        System.out.println(m1.staticValue);//11
    }
	
}
