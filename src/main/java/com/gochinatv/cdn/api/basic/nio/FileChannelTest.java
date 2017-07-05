package com.gochinatv.cdn.api.basic.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


/**
 * http://ifeve.com/file-channel/
 * 
 * http://blog.csdn.net/wangxy799/article/details/50897061
 * 
 * http://www.tuicool.com/articles/RziquaY
 * 
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
		//FileChannel channel = new FileOutputStream("d:/").getChannel();
		//RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
		
		
		FileChannel inputChannel = new FileInputStream("d:/channel.txt").getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		int read = inputChannel.read(buffer);//读取的文件字节数
		byte[] array = buffer.array();
		System.out.println(new String(array));
		
	}
}
