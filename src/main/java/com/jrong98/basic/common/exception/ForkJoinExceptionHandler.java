package com.jrong98.basic.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ajie
 * @date 2022/6/21
 */
public class ForkJoinExceptionHandler implements Thread.UncaughtExceptionHandler {

    public final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        log.error("ForkJoinPool Exception", e);
    }
}
