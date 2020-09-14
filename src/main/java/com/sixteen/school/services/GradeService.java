package com.sixteen.school.services;

import com.sixteen.school.model.Grade;
import com.sixteen.school.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {
    @Autowired
    private GradeRepository gradeRepository;

    public Grade addGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    public Grade updateGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    public void removeGrade(long id) {
        gradeRepository.deleteById(id);
    }

    public Grade getGradeById(Long  id) {
        return gradeRepository.findById(id).get();
    }

    public List<Grade> getList(){
        return gradeRepository.findAll();
    }
    public Page<Grade> getPageList(Pageable pageable){
        Page<Grade> gradeList= gradeRepository.findAll(pageable);
        return gradeList ;
    }
}
