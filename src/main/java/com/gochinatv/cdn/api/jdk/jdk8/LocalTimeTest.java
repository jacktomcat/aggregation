package com.gochinatv.cdn.api.jdk.jdk8;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * 
 * https://dotnetcodr.com/2015/01/11/formatting-dates-in-java-8-using-datetimeformatter/
 * @author jacktomcat
 *
 */
public class LocalTimeTest {


    /**
     *
     * of： 静态工厂方法，从组成部分中创建实例
     * from： 静态工厂方法，尝试从相似对象中提取实例。from()方法没有of()方法类型安全
     * now： 静态工厂方法，用当前时间创建实例
     * parse： 静态工厂方法，总字符串解析得到对象实例
     * get： 获取时间日期对象的部分状态
     * is： 检查关于时间日期对象的描述是否正确
     * with： 返回一个部分状态改变了的时间日期对象拷贝
     * plus： 返回一个时间增加了的、时间日期对象拷贝
     * minus： 返回一个时间减少了的、时间日期对象拷贝
     * to： 把当前时间日期对象转换成另外一个，可能会损失部分状态
     * at： 用当前时间日期对象组合另外一个，创建一个更大或更复杂的时间日期对象
     * format： 提供格式化时间日期对象的能力
     *
     * @param args
     */
    public static void main(String[] args) {
		
        System.out.println("=====================LocalTime=======================");
        LocalTime now = LocalTime.now();

        System.out.println("LocalTime truncatedTo= "+now.truncatedTo(ChronoUnit.MINUTES));//截取到分钟
        System.out.println("LocalTime of = "+LocalTime.of(12, 12));//设置时间



	}
}
