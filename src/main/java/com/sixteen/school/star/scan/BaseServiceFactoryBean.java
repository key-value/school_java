package com.sixteen.school.star.scan;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Proxy;

public class BaseServiceFactoryBean<T> implements FactoryBean<T>, ApplicationContextAware {
    /**
     * 要注入的接口类定义
     */

    private Class<T> interfaceClass;

    /**
     * Spring上下文
     */
    private ApplicationContext applicationContext;

    //也因该走工厂方法注入得来

    private MultiBaseService multiBaseService;

    public void setInterfaceClass(Class<T> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }


    public void setMultiBaseService(MultiBaseService multiBaseService) {
        this.multiBaseService = multiBaseService;
    }

    public BaseServiceFactoryBean(Class<T> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    @Override
    public T getObject() throws Exception {
        //采用动态代理生成接口实现类，核心实现`
        return (T) Proxy.newProxyInstance(applicationContext.getClassLoader(), new Class[]{interfaceClass}, new MethodProxy(multiBaseService, interfaceClass));
    }

    @Override
    public Class<?> getObjectType() {
        return this.interfaceClass;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


}
