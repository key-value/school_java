package com.sixteen.school.services;

import com.sixteen.school.model.Teacher;
import com.sixteen.school.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public void removeTeacher(long id) {
        teacherRepository.deleteById(id);
    }

    public Teacher getTeacherById(Long  id) {
        return teacherRepository.findById(  id).get();
    }

    public List<Teacher> getList(){
        return teacherRepository.findAll();
    }
    public Page<Teacher> getPageList(Pageable pageable){
        Page<Teacher> teacherList= teacherRepository.findAll(pageable);
        return teacherList ;
    }
}
