package com.sixteen.school.Control;

import com.sixteen.school.model.Teacher;
import com.sixteen.school.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TeacherController {

    @Autowired
    public TeacherService teacherService;

    @PostMapping()
    long addTeacher(Teacher teacher) {
        return teacherService.addTeacher(teacher);
    }

    int updateTeacher(Teacher teacher) {
        return teacherService.updateTeacher(teacher);
    }

    int removeTeacher(long id) {
        return teacherService.removeTeacher(id);
    }

    Teacher getTeacherById(long  id) {
        return teacherService.getTeacherById(id);
    }

    List<Teacher> getList(){
        return teacherService.getList();
    }
}
