package com.gochinatv.cdn.api.displacement;


/**
 * java 位移应用
 * @author jack
 *
 */
public class DisplacementTest {
   
	 public static void main(String[] args) {
		 int search = 1;    //查询
		 int save = 2;      //添加
		 int update = 4;    //更新
		 int delete = 8;    //删除
         		 
		 int authority = 7;  //数据库中保存为7  判断具有哪些 权限
		 
		 int resultOr = authority | search ;
		 System.out.println("查询权限====="+resultOr);  //7      0111|0001  = 0111(真真为真, 真假为真, 假假为假)
		 
		 resultOr = authority | save ;
		 System.out.println("添加权限====="+resultOr);   //7     0111|0010  = 0111(真真为真, 真假为真, 假假为假)
		 
		 resultOr = authority | update ;
		 System.out.println("修改权限====="+resultOr);  //7      0111|0100  = 0111(真真为真, 真假为真, 假假为假)
		 
		 resultOr = authority | delete ;
		 System.out.println("删除权限====="+resultOr);  //15     0111|1000  = 1111(真真为真, 真假为真, 假假为假)
		 
		 
		 //判断权限会用下面的形式来判断是否具有权限  
		 int resultAnd = authority & search ;
		 System.out.println("查询权限====="+resultAnd);//1          0111|0001  = 0001(真真为真, 真假为假, 假假为假)
		 
		 resultAnd = authority & save ;
		 System.out.println("添加权限====="+resultAnd);//2          0111|0010  = 0010(真真为真, 真假为假, 假假为假)
		 
		 resultAnd = authority & update ;
		 System.out.println("修改权限====="+resultAnd);//4          0111|0100  = 0100(真真为真, 真假为假, 假假为假)
		 
		 resultAnd = authority & delete ;
		 System.out.println("删除权限====="+resultAnd);//0          0111|1000  = 0000(真真为真, 真假为假, 假假为假)
		 
		 
	 }   
	 
}
