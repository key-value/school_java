package com.sixteen.school.star;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract class CombinationService<M,T> {

    private JpaRepository<M, Long> jpaRepository;

    private  UnifiedService<M> unifiedService;
}
