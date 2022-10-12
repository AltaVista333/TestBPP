package com.example.aop;

import org.springframework.stereotype.Component;

@Component
public class SomeClass implements SomeInterface{
    @MethodNameAnnotation
    public void someMethod() {
        System.out.println("Some method");
    }
}
