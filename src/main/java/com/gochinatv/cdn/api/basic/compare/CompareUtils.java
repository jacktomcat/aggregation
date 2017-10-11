package com.gochinatv.cdn.api.basic.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author jack
 *
 * Comparable & Comparator 都是用来实现集合中元素的比较、排序的，只是 Comparable 是在集合内部定义的方法实现的排序，
 * Comparator 是在集合外部实现的排序，所以，如想实现排序，就需要在集合外定义 Comparator 接口的方法或在集合内实现 
 * Comparable 接口的方法。
 * 
 */
public class CompareUtils {

	
	public static void main(String[] args) {
		Book b3 = new Book(10002, "ab水浒传", 100.8);  
		Book b1 = new Book(10000, "a红楼梦", 150.86); 
		Book b2 = new Book(10001, "b三国演义", 99.68);  
	    Book b5 = new Book(10004, "c天龙八部", 10.4); 
	    Book b4 = new Book(10003, "ac西游记", 100.8);  
		
	    List<Book> bookList = new ArrayList<>();
	    bookList.add(b1);
	    bookList.add(b2);
	    bookList.add(b3);
	    bookList.add(b4);
	    bookList.add(b5);
	    
	    //按id排序，book实现了 Comparable
	    Collections.sort(bookList);
	    System.out.println("========按id排序=======");
	    print(bookList);
	    
	    //按价格排序，  priceComparator 实现了 Comparator
	    Collections.sort(bookList,new priceComparator());
	    System.out.println("========按价格排序=======");
	    print(bookList);
	}
	
	
	public static void print(List<Book> bookList){
		for (Book book : bookList) {
			System.out.println(book);
		}
	}
	
}



class Book implements Comparable<Book>{
	public int id;// 编号
	public String name;// 名称
	public double price; // 价格

	
	public Book(int id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
	/**
	 * 1) 如果要按照升序排序
	 * 	o1 小于o2，返回-1（负数），相等返回0， o1 大于o2 返回1（正数） 
	 * 
	 * 2) 如果要按照降序排序 
	 *	o1 小于o2，返回1（正数），相等返回0， o1 大于o2返回-1（负数） 
	 * 
	 */
	public int compareTo(Book o) {// Comparable接口中的方法  
	   //先按价格升序,然后按照名词升序
       if(this.price>o.price){
    	   return 1;
       }
       if(this.price<o.price){
    	   return -1;
       }
       if(this.name.compareTo(o.name)<0){
    	   return -1;
       }
       if(this.name.compareTo(o.name)>0){
    	   return 1;
       }
       return 0;
    }

	@Override
	public String toString() {
		return "id:"+this.id+",name:"+this.name+",price:"+this.price;
	}
	
}

class priceComparator implements Comparator<Book>{

	@Override
	public int compare(Book o1, Book o2) {
		//return new Double(o1.price).compareTo(new Double(o2.price));
		if(o1.price>o2.getPrice()){
			return 1;
		}else if(o1.price<o2.getPrice()){
			return -1;
		}
		return 0;
	}
}
