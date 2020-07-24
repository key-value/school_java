package com.sixteen.school.Control;

import com.sixteen.school.model.Teacher;
import com.sixteen.school.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/teacher", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TeacherController {

    @Autowired
    public TeacherService teacherService;

    @PostMapping()
    long addTeacher(Teacher teacher) {
        return teacherService.addTeacher(teacher);
    }

    @PutMapping()
    int updateTeacher(Teacher teacher) {
        return teacherService.updateTeacher(teacher);
    }

    @DeleteMapping()
    int removeTeacher(long id) {
        return teacherService.removeTeacher(id);
    }

    @GetMapping(path = "/{id}")
    Teacher getTeacherById(@PathVariable(value="id") Long  id) {
        Teacher teacher =  teacherService.getTeacherById(id);
        return teacher;
    }

    @GetMapping()
    List<Teacher> getList(){
        return teacherService.getList();
    }
}
