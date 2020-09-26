package com.sixteen.school.star.scan;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class SpecialBeanForUnified implements BeanFactoryPostProcessor  {


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
// System.getProperty("java.class.path")


//        String BASE_PACKAGE = "com.sixteen.school";
//        GenericApplicationContext context = new GenericApplicationContext();
//        MyClassPathDefinitonScanner myClassPathDefinitonScanner = new MyClassPathDefinitonScanner(context, MultiSign.class);
//// 注册过滤器
//        myClassPathDefinitonScanner.registerTypeFilter();
//        int beanCount = myClassPathDefinitonScanner.scan(BASE_PACKAGE);
//        context.refresh();
//        String[] beanDefinitionNames = context.getBeanDefinitionNames();
//        System.out.println("beanCount");
//        System.out.println(beanCount);
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
//        }

//        BeanDefinitionRegistry bdr = (BeanDefinitionRegistry) beanFactory;
//        GenericBeanDefinition gbd = new GenericBeanDefinition();
//        gbd.setBeanClass(MethodProxy.class);
//        gbd.setScope(BeanDefinition.SCOPE_SINGLETON);
//        gbd.setAutowireCandidate(true);
//        bdr.registerBeanDefinition("methodProxy", gbd);
    }

}