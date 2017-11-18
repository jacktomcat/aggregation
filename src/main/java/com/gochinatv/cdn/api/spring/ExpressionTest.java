package com.gochinatv.cdn.api.spring;

import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;


public class ExpressionTest {

	/**
	 * 聚合测试  +,-,*,/
	 */
	@Test
	public void aggr() {
		ExpressionVo vo = new ExpressionVo(100.3f, 23.5f, 50, "zhangsan");

		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("(sum+avg)/count");// 表达式相加,相除
		Float value = (Float) exp.getValue(vo);
		System.out.println(value);
	}
	
	/**
	 * boolean test
	 */
	@Test
	public void bool() {
		ExpressionVo vo = new ExpressionVo(100.3f, 23.5f, 50, "zhangsan");

		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("sum>100 && avg>50");// 表达式相加,相除
		Boolean value = (Boolean) exp.getValue(vo);
		System.out.println(value);
	}

	
	/**
	 * boolean test invoke method
	 */
	@Test
	public void externalMethod() {
		ExpressionVo vo = new ExpressionVo(100.3f, 23.5f, 50, "zhangsan");
		ExpressionTest test = new ExpressionTest();
		
		ExpressionParser parser = new SpelExpressionParser();
		
		float v = vo.getAvg();
		
		Boolean value = parser.parseExpression("isTrue("+v+")").getValue(test,Boolean.class);
		System.out.println(value);
	}
	
	
	public boolean isTrue(float sum){
		return (sum>100 || sum<20);
	}
	
}

class ExpressionVo {

	public ExpressionVo(float sum, float avg, int count, String name) {
		super();
		this.sum = sum;
		this.avg = avg;
		this.count = count;
		this.name = name;
	}

	private float sum;
	private float avg;
	private int count;
	private String name;

	public float getSum() {
		return sum;
	}

	public void setSum(float sum) {
		this.sum = sum;
	}

	public float getAvg() {
		return avg;
	}

	public void setAvg(float avg) {
		this.avg = avg;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}