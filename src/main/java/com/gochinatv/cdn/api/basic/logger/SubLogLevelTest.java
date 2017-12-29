package com.gochinatv.cdn.api.basic.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jacktomcat on 17/12/29.
 */
public class SubLogLevelTest extends LogLevelTest{

    final static Logger logger = LoggerFactory.getLogger(SubLogLevelTest.class);

    public static void main(String[] args) {
        logger.info("SubLogLevelTest start ");

        SubLogLevelTest sub = new SubLogLevelTest();
        sub.testDebugLevel();

        logger.info("SubLogLevelTest end ");
    }


}
