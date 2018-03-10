package com.example.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    @Autowired
    private ApplicationContext context;

    public ApplicationContext getContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        System.out.println("---------------------------------------------" + context);
        this.context = context;
    }

    public  <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }


    @SuppressWarnings("unchecked")
    public  <T> T getBean(String name) {
        System.out.println(context + "benazzzzzzzzzzzzz");
        return (T) context.getBean(name);
    }

}