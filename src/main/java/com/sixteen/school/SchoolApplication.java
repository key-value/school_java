package com.sixteen.school;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication()
@EnableCaching
@EnableJpaAuditing
public class SchoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolApplication.class, args);

         Log logger = LogFactory.getLog("11");
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX;
        logger.info(packageSearchPath);
    }

//        PlanService a = CglibProxy.getInstance().getInstance().getProxy(PlanService.class);
//        MethodProxy m=new MethodProxy();
//        Object newProxyInstance = Proxy.newProxyInstance(ITestService.class.getClassLoader(),  new Class[]{ITestService.class},m);
//
//        ITestService ud=((ITestService)newProxyInstance);
//        String s= ud.getName();
//        System.out.println(s);
//         s= ud.say();
//        System.out.println(s);



}