package com.gochinatv.cdn.api.basic.nio;

import java.io.FileOutputStream;
import java.nio.channels.FileChannel;


/**
 * http://ifeve.com/file-channel/
 * @author jacktomcat
 *
 */
public class FileChannelTest {
   
	
	/**
	 * 在使用FileChannel之前，必须先打开它。但是，我们无法直接打开一个FileChannel，
	 * 需要通过使用一个InputStream、OutputStream或RandomAccessFile来获取一个FileChannel实例。
	 * 下面是通过RandomAccessFile打开FileChannel的示例：
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		FileChannel channel = new FileOutputStream("d:/").getChannel();
		
		
	}
}
