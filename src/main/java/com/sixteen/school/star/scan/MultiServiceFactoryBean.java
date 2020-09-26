package com.sixteen.school.star.scan;

import org.springframework.beans.factory.FactoryBean;

public class MultiServiceFactoryBean implements FactoryBean<MultiService> {

    @Override
    public MultiService getObject() throws Exception {
        return new MultiBaseService();
    }

    @Override
    public Class<?> getObjectType() {
        return MultiService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }


}
