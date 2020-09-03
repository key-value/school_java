package com.sixteen.school.star.mvc;

import com.sixteen.school.star.DefaultExceptionResultResolver;
import com.sixteen.school.star.ExceptionResultResolver;
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
}
