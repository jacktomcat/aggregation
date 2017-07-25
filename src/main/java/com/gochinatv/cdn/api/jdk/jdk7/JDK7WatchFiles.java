package com.gochinatv.cdn.api.jdk.jdk7;

import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 * 1. 旧版本监控文件变化的弊端：
 *   1) 非常繁琐，必须自己手动开启一个后台线程每隔一段时间遍历一次目标节点并记录当前状态，然后和上一次遍历的状态对比，如果不相同就表示发生了变化，再采取相应的操作，这个过程非常长，都需要用户自己手动实现；
 *   2) 效率低：效率都消耗在了遍历、保存状态、对比状态上了！这是因为旧版本的Java无法很好的利用OS文件系统的功能，因此只能这样笨拙地监控文件变化；
 *   3) 无法利用OS的很多功能：上一点已经阐明；
 *   
 *   
 * 2. WatchService：
 *   1) 该类的对象就是操作系统原生的文件系统监控器！我们都知道OS自己的文件系统监控器可以监控系统上所有文件的变化，这种监控是无需遍历、无需比较的，是一种基于信号收发的监控，因此效率一定是最高的；现在Java对其进行了包装，可以直接在Java程序中使用OS的文件系统监控器了；
 *   2) 获取当前OS平台下的文件系统监控器：
 *        i. WatchService watcher = FileSystems.getDefault().newWatchService();
 *        ii. 从FileSystems这个类名就可以看出这肯定是属于OS平台文件系统的，接下来可以看出这一连串方法直接可以得到一个文件监控器；
 *          ！！这里暂时不用深入理解这串方法的具体含义，先知道怎么用就行了；
 *   3) 我们都知道，操作系统上可以同时开启多个监控器，因此在Java程序中也不例外，上面的代码只是获得了一个监控器，你还可以用同样的代码同时获得多个监控器；
 *   4) 监控器其实就是一个后台线程，在后台监控文件变化所发出的信号，这里通过上述代码获得的监控器还只是一个刚刚初始化的线程，连就绪状态都没有进入，只是初始化而已；
 *   
 * @author jack
 *
 */
public class JDK7WatchFiles {
   
	public static void main(String[] args) throws Exception {
		WatchService watcher = FileSystems.getDefault().newWatchService();  
		Paths.get("/Users/zhuhuihui/file-visitor").register(watcher, 
				StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_MODIFY);
		while(true){
			//WatchKey poll = watcher.poll();
			WatchKey take = watcher.take();
			for (WatchEvent<?> event: take.pollEvents()) { 
                System.out.println(event.context() + " comes to " + event.kind());  
            }  
              
            boolean valid = take.reset();  
            if (!valid) {  
                break;  
            }
		}
	}
}
