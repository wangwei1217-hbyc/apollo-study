package com.apollo.demo.spring.springBootDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by wangwei on 2019/8/30 0030.
 */
@SpringBootApplication(scanBasePackages = {"com.apollo.demo.spring"})
public class SpringBootSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootSampleApplication.class,args);
    }
}
