package com.gochinatv.cdn.api.jdk.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * http://blog.csdn.net/yohoph/article/details/42041729
 *
 * -XX:+PrintGCDetails 开关，可以详细了解GC中的变化
 * -XX:+PrintGCTimeStamps 开关，可以了解这些垃圾收集发生的时间，自JVM启动以后以秒计量
 * -XX:+PrintGCDateStamps GC发生的时间信息
 * -XX:+PrintHeapAtGC 开关了解堆的更详细的信息
 * -XX:+HeapDumpOnOutOfMemoryError 堆内存溢出的时候打印错误的堆信息
 * -XX:HeapDumpPath 堆内存溢出打印日志的位置,比如输出的地址为 /Users/zhuhuihui 文件名为java的pid.hprof
 *
 *
 * @author jack
 * -Xms20m -Xmx20m -Xss160k -XX:NewSize=5M -XX:MaxNewSize=6M -XX:+PrintHeapAtGC -XX:+PrintGCTimeStamps -XX:+PrintGCDetails 
 * -XX:+PrintGCDateStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:/logs
 *
 * java -X 非标准输出
 */
public class HeapOOM {
   
	public static void main(String[] args) {
		int number = 0;
		List<byte[]> byteList = new ArrayList<>();
		while(true){
			byte[] bt = new byte[1024*1024*1];//每次分配1M空间
			byteList.add(bt);
			number++;
			System.out.println(number);
		}
	}
	
	
	public static void cacualte(){
		int start = HexToInt("0x00000000ffa00000");
		int end = HexToInt("0x00000000fff00000");
		System.out.println("16进制转换为10进制内存位置开始值:"+start);
		System.out.println("16进制转换为10进制内存位置结束值:"+end);
		System.out.println("内存开始位置至结束位置总空间大小为:"+(end-start)/1024/1024+"M");
	}
	
    //16进制转10进制  
    public static int HexToInt(String strHex){  
        int nResult = 0;  
        String str = strHex.toUpperCase();  
        if ( str.length() > 2 ){  
            if ( str.charAt(0) == '0' && str.charAt(1) == 'X' ){  
                str = str.substring(2);  
            }  
        }  
        int nLen = str.length();  
        for ( int i=0; i<nLen; ++i ){  
            char ch = str.charAt(nLen-i-1);  
            try {  
                nResult += (GetHex(ch)*GetPower(16, i));  
            } catch (Exception e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
        return nResult;  
    } 
    
    
    //计算16进制对应的数值  
    public static int GetHex(char ch) throws Exception{  
        if ( ch>='0' && ch<='9' )  
            return (int)(ch-'0');  
        if ( ch>='a' && ch<='f' )  
            return (int)(ch-'a'+10);  
        if ( ch>='A' && ch<='F' )  
            return (int)(ch-'A'+10);  
        throw new Exception("error param");  
    }  
      
    //计算幂  
    public static int GetPower(int nValue, int nCount) throws Exception{  
        if ( nCount <0 )  
            throw new Exception("nCount can't small than 1!");  
        if ( nCount == 0 )  
            return 1;  
        int nSum = 1;  
        for ( int i=0; i<nCount; ++i ){  
            nSum = nSum*nValue;  
        }  
        return nSum;  
    } 
	
	/**
	 * 
	 * 收集之前     invocations 发生收集的次数         (full 0) 发生老年代gc的次数,此处为0
	 * 
	 * {Heap before GC invocations=1 (full 0):
	 *	 PSYoungGen      total 5632K, used 4351K [0x00000000ffa00000, 0x0000000100000000, 0x0000000100000000)
	 *	  	eden space 5120K, 84% used [0x00000000ffa00000,0x00000000ffe3fce0,0x00000000fff00000)
	 *	  	from space 512K, 0% used [0x00000000fff80000,0x00000000fff80000,0x0000000100000000)
	 *	  	to   space 512K, 0% used [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000)
	 *	 ParOldGen       total 14336K, used 0K [0x00000000fec00000, 0x00000000ffa00000, 0x00000000ffa00000)
	 *	  	object space 14336K, 0% used [0x00000000fec00000,0x00000000fec00000,0x00000000ffa00000)
	 *	 Metaspace       used 2675K, capacity 4486K, committed 4864K, reserved 1056768K
	 *	  	class space    used 287K, capacity 386K, committed 512K, reserved 1048576K
	 *	
	 * 具体收集详情, (Allocation Failure) 分配失败         4351K->502K(5632K)   分别表示:收集之前占用的空间,收集之后的占用的空间,该区域总空间
	 * 2017-09-26T09:57:16.341+0800: 0.221: [GC (Allocation Failure) [PSYoungGen: 4351K->502K(5632K)] 4351K->3799K(19968K), 0.0014352 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
	 *	
	 * 收集之后      invocations 发生收集的次数         (full 0) 发生老年代gc的次数,此处为0
	 * 
	 * Heap after GC invocations=1 (full 0):
	 *	 PSYoungGen      total 5632K, used 502K [0x00000000ffa00000, 0x0000000100000000, 0x0000000100000000)
	 *	  	eden space 5120K, 0% used [0x00000000ffa00000,0x00000000ffa00000,0x00000000fff00000)
	 *	  	from space 512K, 98% used [0x00000000fff00000,0x00000000fff7dbc8,0x00000000fff80000)
	 *	 	 to   space 512K, 0% used [0x00000000fff80000,0x00000000fff80000,0x0000000100000000)
	 *	 ParOldGen       total 14336K, used 3296K [0x00000000fec00000, 0x00000000ffa00000, 0x00000000ffa00000)
	 *	  	object space 14336K, 22% used [0x00000000fec00000,0x00000000fef38040,0x00000000ffa00000)
	 *	 Metaspace       used 2675K, capacity 4486K, committed 4864K, reserved 1056768K
	 *	  	class space    used 287K, capacity 386K, committed 512K, reserved 1048576K
	 *	}
	 * 
	 * 
	 * 分表表示16位的内存    开始的位置,当前位置,结束位置
	 * int start = HexToInt("0x00000000ffa00000");
	 * int current = HexToInt("0x0000000100000000");
	 * int end = HexToInt("0x0000000100000000");
	 * 
	 * used:   (current-start)/1024/1024=xx `M`
	 * total:  (end-start)/1024/1024=xx `M`
	 * remain: (end-current)/1024/1024=xx `M`
	 * [0x00000000ffa00000, 0x0000000100000000, 0x0000000100000000)
	 * 
	 * 
	 */
	
}
