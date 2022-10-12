package com.example.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AopApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(AopApplication.class, args);
        SomeInterface bean = ctx.getBean(SomeInterface.class);
        bean.someMethod();

        System.out.println("tttt");
        bean.someMethod();

    }

}
