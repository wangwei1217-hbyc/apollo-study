package com.apollo.demo.spring.javaConfigDemo;

import com.apollo.demo.spring.bean.AnnotatedBean;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by wangwei on 2019/8/30 0030.
 */
public class AnnotationApplication {
    public static void main(String[] args) throws Exception{
        ApplicationContext context = new AnnotationConfigApplicationContext("com.apollo.demo.spring");
        AnnotatedBean annotatedBean = context.getBean(AnnotatedBean.class);

        System.out.println("AnnotationApplication Demo. Input any key except quit to print the values. Input quit to exit.");
        while (true) {
            System.out.print("> ");
            String input = new BufferedReader(new InputStreamReader(System.in, Charsets.UTF_8)).readLine();
            if (!Strings.isNullOrEmpty(input) && input.trim().equalsIgnoreCase("quit")) {
                System.exit(0);
            }

            System.out.println(annotatedBean.toString());
        }
    }
}
