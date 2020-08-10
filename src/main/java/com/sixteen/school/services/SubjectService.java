package com.sixteen.school.services;

import com.sixteen.school.model.Subject;
import com.sixteen.school.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public Subject addSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject updateSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public void removeSubject(long id) {
         subjectRepository.deleteById(id);
    }

    public Subject getSubjectById(Long  id) {
        return subjectRepository.findById(id).get();
    }

    public List<Subject> getList(){
        return subjectRepository.findAll();
    }

    public Page<Subject> getPageList(Pageable pageable){
        Page<Subject> subjectList= subjectRepository.findAll(pageable);
        return subjectList ;
    }
}
