package com.sixteen.school.star;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class GeneratorBeanConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext
                .getAutowireCapableBeanFactory();
        this.registBeanByFactory(beanFactory);
    }

    private void registBeanByFactory(DefaultListableBeanFactory beanFactory) {
//        System.out.println("IdGeneratorBeanConfig.registBeanByFactory()");
//        Map<String, Object> beansWithAnnotationMap = this.applicationContext.getBeansWithAnnotation(MultiService.class);
//
//        Class<? extends Object> clazz = null;
//        for (Map.Entry<String, Object> entry : beansWithAnnotationMap.entrySet()) {
//            clazz = entry.getValue().getClass();//获取到实例对象的class信息
//            Class<? extends Object>[] interfaces = clazz.getInterfaces();
//            for (Class<? extends Object> aInterface : interfaces) {
//                //接口信息
//                Object aIn = aInterface;
//            }
//        }
    }

    /**
     * 容器初始化完成后进行添加bean
     *
     * @param beanClass
     */
    private void registerBean(DefaultListableBeanFactory beanFactory, String beanName, Class<?> beanClass) {
        AnnotatedBeanDefinition annotatedBeanDefinition = new AnnotatedGenericBeanDefinition(beanClass);
//        ((AnnotatedGenericBeanDefinition) annotatedBeanDefinition).setPropertyValues(getPropertyValues(seqEnum));//添加属性
        beanFactory.registerBeanDefinition(beanName, annotatedBeanDefinition);
    }


//    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();
//    @Override
//    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
//        System.out.println("NetsDaoBeanConfig.postProcessBeanDefinitionRegistry()");
//        this.daoRegisterBean(registry);
//    }
//
//    private  void daoRegisterBean(BeanDefinitionRegistry registry){
//        Set<Class> scan = new HashSet<>();
//        for (Class tl : scan) {
//            registerBean(registry, null, tl);
//        }
//    }
//
//    /**
//     * 提供公共的注册方法。
//     *
//     * @param registry
//     * @param name
//     * @param beanClass
//     */
//    private void registerBean(BeanDefinitionRegistry registry, String name, Class<?> beanClass) {
//        AnnotatedBeanDefinition annotatedBeanDefinition = new AnnotatedGenericBeanDefinition(beanClass);
//        annotatedBeanDefinition.setParentName("baseDao");
//        //自动生成name
//        String beanName = (name != null ? name : this.beanNameGenerator.generateBeanName(annotatedBeanDefinition, registry));
//        System.out.println("生成的beanName：" + beanName);
//        //bean注册的holer类.
//        BeanDefinitionHolder beanDefinitionHolder = new BeanDefinitionHolder(annotatedBeanDefinition, beanName);
//        //使用bean注册工具类进行注册.
//        BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinitionHolder, registry);
//    }
//
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
//        System.out.println("NetsDaoBeanConfig.postProcessBeanFactory()");
//    }
}
