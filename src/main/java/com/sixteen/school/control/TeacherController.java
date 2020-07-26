package com.sixteen.school.control;

import com.github.pagehelper.PageInfo;
import com.sixteen.school.model.Teacher;
import com.sixteen.school.services.TeacherService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping(value = "v1/teacher", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TeacherController {

    @Autowired
    public TeacherService teacherService;

    @PostMapping()
    long addTeacher(String name) {
        Teacher teacher = new Teacher();
        teacher.setTeacherName(name);
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
    Teacher getTeacherById(@PathVariable(value = "id") Long id) {
        Teacher teacher = teacherService.getTeacherById(id);
        return teacher;
    }

    @GetMapping()
    List<Teacher> getList() {
        return teacherService.getList();
    }

    @GetMapping(path = "/page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", paramType = "query", value = "1"),
            @ApiImplicitParam(name = "size", paramType = "query", value = "10")
    })
    PageInfo<Teacher> getPageList(@ApiIgnore @PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC)
                                          Pageable pageable) {
        return teacherService.getPageList(pageable.getPageSize(), pageable.getPageNumber());
    }
}
