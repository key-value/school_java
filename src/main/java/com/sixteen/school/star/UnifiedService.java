package com.sixteen.school.star;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class UnifiedService<M> {

    private JpaRepository<M, Long> jpaRepository;


    public UnifiedService(JpaRepository<M, Long> repository) {
        jpaRepository = repository;
    }

    public M addT(M grade) {
        return jpaRepository.save(grade);
    }

    public M updateT(M grade) {
        return jpaRepository.save(grade);
    }

    public void remove(Long id) {
        jpaRepository.deleteById(id);
    }

    public M getById(Long id) {
        return jpaRepository.findById(id).get();
    }

    public List<M> getList() {
        return jpaRepository.findAll();
    }

    public Page<M> getPageList(Pageable pageable) {
        Page<M> gradeList = jpaRepository.findAll(pageable);
        return gradeList;
    }
}
