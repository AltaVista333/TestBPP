package com.example.aop;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class MethodNameBeanPostProcessor implements BeanPostProcessor {


    private Map<String, Class> map = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        Method[] methods = beanClass.getMethods();
        for (var m: methods) {
            MethodNameAnnotation annotation = m.getAnnotation(MethodNameAnnotation.class);
            if (annotation != null){
                map.put(beanName, beanClass);
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

       Class beanClass = map.get(beanName);
       if (beanClass != null){
           return Proxy.newProxyInstance(
                   bean.getClass().getClassLoader(),
                   beanClass.getInterfaces(),
                   (proxy, method, args) -> {
                       System.out.println("Method methodname: " + method.getName());
                       return method.invoke(bean, args);
                   }
           );
       }
        return bean;
    }




}
