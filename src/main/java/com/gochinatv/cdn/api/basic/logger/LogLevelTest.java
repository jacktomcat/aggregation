package com.gochinatv.cdn.api.basic.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogLevelTest {
	
	 Logger logger = LoggerFactory.getLogger(this.getClass());
	 Logger sysout = LoggerFactory.getLogger("System.out");
	 Logger syserr= LoggerFactory.getLogger("System.err");
	
	public static void main(String[] args) {
		LogLevelTest t = new LogLevelTest();
		t.testDebugLevel();
	}

	/**
	 * com.gochinatv.cdn.api logback.xml 指定日志级别
	 */
	private void testDebugLevel(){
		//System.setOut(new LoggerPrintStream(sysout));
    	System.setErr(new LoggerPrintStream(syserr)); //如果把System.err写入到日志文件,需要自定义一个PrintStream
    	
		System.err.println("============调试日志级别开始==========");
		logger.trace("***********trace");//比 DEBUG 级别的粒度更细
		logger.debug("***********debug");
		logger.info("***********info");
		logger.warn("***********warn");
		logger.error("***********error");
	}
}
