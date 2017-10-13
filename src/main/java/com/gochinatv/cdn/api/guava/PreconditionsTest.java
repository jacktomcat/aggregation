package com.gochinatv.cdn.api.guava;

import java.util.Set;

import org.junit.Test;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

/**
 * 
 * @author jacktomcat
 *
 */
public class PreconditionsTest {

	
	@Test
	public void checkNotNullTest(){
		Object object = null;
		Preconditions.checkNotNull(object,"不能为空");
		System.out.println(object);
	}
	
	@Test
	public void checkArgumentTest(){
		String message = "";
		Preconditions.checkArgument(message.length()>5,"长度不能低于5位");
	}
	
	
	@Test
	public void checkStateTest(){
		Preconditions.checkState(5==4,"必须为true");
	}
	
}
