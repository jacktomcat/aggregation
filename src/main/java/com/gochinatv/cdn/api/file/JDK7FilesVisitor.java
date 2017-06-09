package com.gochinatv.cdn.api.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;


/**
 * 1. 遍历文件和目录——FileVisitor：
 *   1) 在旧版本中遍历文件系统只能通过递归的方法来实现，但是这种方法不仅消耗资源大而且效率低；
 *   2) NIO.2的Files工具类提供了一个静态工具方法walkFileTree来高效并优雅地遍历文件系统；
 *   3) walkFileTree：
 *        i. 原型：static Path Files.walkFileTree(Path start, FileVisitor<? super Path> visitor);
 *        ii. 表示从start代表的节点开始遍历文件系统；
 *        iii. 其中visitor是遍历过程中的行为控制器；
 *   4) 遍历行为控制器——FileVisitor：
 *        i. 它是一个接口，里面定义了4个方法用来指定当你访问一个节点之前、之中、之后、失败时应该采取什么行动；
 *        ii. 这个设计非常优雅和科学，毕竟你在遍历文件系统时想要做的事情无外乎发生在这几个时间点上，Java全部为你考虑好了，并搭好了框架！多么的贴心！！
 * @author jack 下面为文件结构
 * file-visitor
 *	├── 2017
 *	│   ├── aaa.txt
 *	│   └── bbb.txt
 *	├── 2018
 *	│   ├── 01
 *	│   │   ├── 001
 *	│   │   │   └── 001.txt
 *	│   │   └── abc.txt
 *	│   ├── 02
 *	│   │   └── 02.txt
 *	│   ├── 03
 *	│   │   └── 03.txt
 *	│   ├── 345.txt
 *	│   └── 555.txt
 *	└── 2019
 *	    ├── ert.txt
 *	    ├── uuu.txt
 *	    └── yyy.txt
 */
public class JDK7FilesVisitor {
   
	public static void main(String[] args) throws Exception {
		Path path = Paths.get("/Users/zhuhuihui/file-visitor");
		System.out.println(path.getRoot());
		Files.walkFileTree(path, new CustomFileVisitor());
	}
}

/**
 * 递归访问指定的文件夹下面的目录，并提供了执行前、执行中、执行后、执行失败的的各种操作
 * @author jack
 *
 */
class CustomFileVisitor implements FileVisitor<Path>{

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		//Objects.requireNonNull(dir);
		/*if(dir.toString().equals("/Users/zhuhuihui/abc/2018/03")){
			return FileVisitResult.TERMINATE;//如果为TERMINATE 则后面的目录都不回访问
		}*/
		/*if(dir.toString().equals("/Users/zhuhuihui/abc/2018/01")){
			return FileVisitResult.SKIP_SUBTREE;//如果为SKIP_SUBTREE 则2018/01下面目录就不会访问了，直接跳过，其他目录照常访问
		}*/
		if(dir.toString().equals("/Users/zhuhuihui/abc/2018/01")){
			return FileVisitResult.SKIP_SIBLINGS;//如果为SKIP_SIBLINGS 则会跳过和2018/01同级的目录，其他的照常访问
		}
		System.out.println("正在访问：" + dir + "目录");
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		//Objects.requireNonNull(file);
		System.out.println(file.getFileName()+"=======");
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		return FileVisitResult.TERMINATE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		return FileVisitResult.CONTINUE;
	}
	
	
}
