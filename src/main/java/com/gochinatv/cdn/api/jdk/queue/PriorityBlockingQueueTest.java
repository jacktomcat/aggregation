package com.gochinatv.cdn.api.jdk.queue;

import org.junit.Test;
import java.util.AbstractQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 实现排序的 BlockingQueue
 * Created by jacktomcat on 17/9/24.
 */
public class PriorityBlockingQueueTest {

    @Test
    public void priorityBlockingQueueTest() {
    	
    	//java 包装器类型都实现了 Comparator,除了char没有包装器类型
        AbstractQueue<CustomSort> queue = new PriorityBlockingQueue<>();
        
        queue.add(new CustomSort(1, "张三"));
        queue.add(new CustomSort(4, "张三4"));
        queue.add(new CustomSort(45, "张三45"));
        queue.add(new CustomSort(56, "张三56"));
        queue.add(new CustomSort(78, "张三78"));
        queue.add(new CustomSort(3, "张三3"));
        
        CustomSort data = null;
        while(null!=(data=queue.poll())){
        	System.out.println(data);
        }
    }

}


class CustomSort implements Comparable<CustomSort>{

	private int id;
	private String name;

	public CustomSort(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CustomSort [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int compareTo(CustomSort o) {
		//System.out.println(o.getId()-this.getId());
		return o.getId()-this.getId();
	}

	

}