package com.sixteen.school.star.mvc;

import com.sixteen.school.star.DefaultExceptionResultResolver;
import com.sixteen.school.star.ExceptionResultResolver;
import com.sixteen.school.star.scan.DefaultClassRegistryBeanFactory;
import com.sixteen.school.star.scan.MultiServiceFactoryBean;
import com.sixteen.school.star.scan.SpecialBeanForUnified;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class MvcConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ExceptionResultResolver resultResolver() {
        return new DefaultExceptionResultResolver();
    }

    @Bean
    @ConditionalOnMissingBean
    SpecialBeanForUnified specialBeanForUnified() {
        return new SpecialBeanForUnified();
    }
    @Bean(name = "MultiServiceFactoryBean")
    @ConditionalOnMissingBean
    MultiServiceFactoryBean multiServiceFactoryBean() {
        return new MultiServiceFactoryBean();
    }
    @Bean
    @ConditionalOnMissingBean
    public DefaultClassRegistryBeanFactory configDefaultClassRegistryBeanFactory() {
        DefaultClassRegistryBeanFactory defaultClassRegistryBeanFactory = new DefaultClassRegistryBeanFactory();
        defaultClassRegistryBeanFactory.setScanPackage("com.sixteen.school");
        defaultClassRegistryBeanFactory.setMapperManagerFactoryBean("MultiServiceFactoryBean");
        return defaultClassRegistryBeanFactory;

    }
}
