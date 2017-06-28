package com.gochinatv.cdn.api.thrift;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransport;
import com.chsoft.datacollection.entity.thrift.User;
import com.chsoft.datacollection.entity.thrift.Users;



/**
 * thrift --gen java user-entity.thrift
 * cd gen-java/namespace 下面
 * 
 * http://blog.csdn.net/xuemengrui12/article/details/60963538
 * 
 * @author jacktomcat
 *
 */

public class ThriftListTest {

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
    	Users list = new Users();
    	long start = System.currentTimeMillis();
    	for(int i=0;i<10000;i++){
    		User user = new User();  
    		user.setId(i);
    		user.setUserName("张三" + i);
    		user.setAge(30);
    		user.setPhone("13139259711");
    		user.setAddress("北京市");
            list.addToUsers(user);
    	}
        
        // 序列化  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        TTransport transport = new TIOStreamTransport(out);  
        TBinaryProtocol tp = new TBinaryProtocol(transport);//二进制编码格式进行数据传输  
        //TCompactProtocol tp = new TCompactProtocol (transport);  
        try {
        	list.write(tp);
        } catch (TException e) {  
            e.printStackTrace();  
        }  
        byte[] buf = out.toByteArray();  
        System.out.println("序列化的对象大小：" + buf.length +"===耗时："+(System.currentTimeMillis()-start));
        return buf;  
    }  
    
    
    /** 
     * 反序列化方法 
     * 
     * @param bis 
     */  
    private static void parse(byte[] bytes) {  
        Users list = new Users();  
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes); 
        TTransport transport = new TIOStreamTransport(bis);  
        TBinaryProtocol tp = new TBinaryProtocol(transport);  
        //TCompactProtocol tp = new TCompactProtocol(transport);  
        try {  
        	list.read(tp);  
            //System.out.println("反序列化后的对象：" + list.toString());  
        } catch (TException e) {  
            e.printStackTrace();  
        }  
    } 

}
