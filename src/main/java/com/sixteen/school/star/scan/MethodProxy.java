package com.sixteen.school.star.scan;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MethodProxy implements InvocationHandler {
    private MultiBaseService baseService;

    private Class<?> interfaceClass;

    public MethodProxy(MultiBaseService baseService, Class<?> interfaceClass) {
        this.baseService = baseService;
        this.interfaceClass = interfaceClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //如果传进来是一个已实现的具体类（本次演示略过此逻辑)
        if (Object.class.equals(method.getDeclaringClass())) {
            try {
                return method.invoke(this, args);
            } catch (Throwable t) {
                t.printStackTrace();
            }
            //如果传进来的是一个接口（核心)
        } else {
            System.out.println(method.getName());
            return run(method, args);
        }
        return null;
    }

    /**
     * 实现接口的核心方法
     *
     * @param method
     * @param args
     * @return
     */
    public Object run(Method method, Object[] args) {
        //TODO
        //如远程http调用
        //如远程方法调用（rmi)
        //....

        return "method call success!";
    }
}