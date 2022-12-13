package com.jrong98.basic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;

import java.util.concurrent.ForkJoinPool;

/**
 * @author jrong98
 */
@SpringBootApplication
@MapperScan(value = "com.jrong98.basic.dao")
public class MainApplication {

    public static void main(String[] args) {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.exceptionHandler", "com.jrong98.basic.common.exception.ForkJoinExceptionHandler");
        new SpringApplicationBuilder(MainApplication.class)
                //.listeners(new ApplicationPidFileWriter())
                .run(args);
    }

}
