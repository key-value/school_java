package com.sixteen.school.control;

import com.sixteen.school.model.Teacher;
import com.sixteen.school.result.QueryResult;
import com.sixteen.school.services.TeacherService;
import com.sixteen.school.star.controlSign;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "v1/teacher", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@controlSign
public class TeacherController {

    @Autowired
    public TeacherService teacherService;

    @PostMapping()
    Teacher addTeacher(@RequestBody Teacher addTeacher) {
        Teacher teacher = new Teacher();
        teacher.setTeacherName(addTeacher.getTeacherName());
        return teacherService.addTeacher(teacher);
    }

    @PutMapping(path = "/{id}")
    Teacher updateTeacher(@PathVariable(value = "id") Teacher teacher,@RequestBody Teacher addTeacher) {
        teacher.setTeacherName(addTeacher.getTeacherName());
        return teacherService.updateTeacher(teacher);
    }

    @DeleteMapping(path = "/{id}")
    void removeTeacher(@PathVariable(value = "id") long id) {
        teacherService.removeTeacher(id);
    }

    @GetMapping(path = "/{id}")
    Teacher getTeacherById(@PathVariable(value = "id") Teacher teacher) {
        return teacher;
    }

    @GetMapping(path = "/Id")
    List<Teacher> getListById(@RequestParam(value = "ids") List<Teacher> teacher) {
        return new ArrayList<>();
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
    QueryResult<Teacher> getPageList(@ApiIgnore @PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC)
                                             Pageable pageable) {
        Page<Teacher> teacherPage = teacherService.getPageList(pageable);
        return new QueryResult<>(teacherPage);
    }

    @GetMapping(path = "/err")
    Integer tryErr(@RequestParam Integer i) throws Exception {
        if (i == null || i == 0) {
            throw new Exception("111");
        }
        return 1;
    }


}
