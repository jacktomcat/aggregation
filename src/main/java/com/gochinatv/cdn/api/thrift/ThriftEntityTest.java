package com.gochinatv.cdn.api.thrift;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransport;
import com.chsoft.datacollection.entity.thrift.User;



/**
 * thrift --gen java user-entity.thrift
 * cd gen-java/namespace 下面
 * 
 * http://blog.csdn.net/xuemengrui12/article/details/60963538
 * 
 * @author jacktomcat
 *
 */

public class ThriftEntityTest {

	public static void main(String[] args) throws IOException {
		byte[] bytes = serial();
        parse(bytes); 
	}

	
	/** 
     * 序列化方法 
     * 
     * @return 
     */  
    private static byte[] serial() {  
        User user = new User();  
        user.setId(1);
		user.setUserName("张三");
		user.setAge(30);
		user.setPhone("13139259711");
		user.setAddress("北京市");  
        System.out.println("序列化之前的对象：" + user.toString());  
        // 序列化  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        TTransport transport = new TIOStreamTransport(out);  
        TBinaryProtocol tp = new TBinaryProtocol(transport);//二进制编码格式进行数据传输  
        //TCompactProtocol tp = new TCompactProtocol (transport);  
        try {  
            user.write(tp);  
        } catch (TException e) {  
            e.printStackTrace();  
        }  
        byte[] buf = out.toByteArray();  
        return buf;  
    }  
    
    
    /** 
     * 反序列化方法 
     * 
     * @param bis 
     */  
    private static void parse(byte[] bytes) {  
        User user = new User();  
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes); 
        TTransport transport = new TIOStreamTransport(bis);  
        TBinaryProtocol tp = new TBinaryProtocol(transport);  
        //TCompactProtocol tp = new TCompactProtocol(transport);  
        try {  
            user.read(tp);  
            System.out.println("反序列化后的对象：" + user.toString());  
        } catch (TException e) {  
            e.printStackTrace();  
        }  
    } 

}
