package com.sixteen.school.star;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    public static CglibProxy proxy=new CglibProxy();
    private CglibProxy(){}

    public static CglibProxy getInstance(){
        return proxy;
    }

    public <T> T getProxy(Class<T> cls){
        return (T) Enhancer.create(cls, this);
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] arg,
                            MethodProxy proxy) throws Throwable {
        Object result=null;
        try {
            before();
            result= proxy.invokeSuper(obj, arg);
            after();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void before(){
        System.out.println("[cglib] Come to someone.");
    }
    public void after(){
        System.out.println("[cglib] Back to his own corner.");
    }
}
