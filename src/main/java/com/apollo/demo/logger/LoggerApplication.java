package com.apollo.demo.logger;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by wangwei on 2019/8/31 0031.
 */
@SpringBootApplication
@EnableApolloConfig
public class LoggerApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoggerApplication.class,args);
    }
}
