package com.gochinatv.cdn.api.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogLevelTest {
	
	 Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		LogLevelTest t = new LogLevelTest();
		t.testDebugLevel();
	}

	/**
	 * com.gochinatv.cdn.api logback.xml 指定日志级别
	 */
	private void testDebugLevel(){
		logger.trace("***********trace");//比 DEBUG 级别的粒度更细
		logger.debug("***********debug");
		logger.info("***********info");
		logger.warn("***********warn");
		logger.error("***********error");
	}
}
