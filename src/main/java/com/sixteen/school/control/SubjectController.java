package com.sixteen.school.control;

import com.sixteen.school.model.Subject;
import com.sixteen.school.services.SubjectService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping(value = "v1/subject")
public class SubjectController {
    @Autowired
    public SubjectService subjectService;

    @PostMapping()
    Subject addSubject(String name) {
        Subject subject = new Subject();
        subject.setSubjectName(name);
        return subjectService.addSubject(subject);
    }

    @PutMapping()
    Subject updateSubject(Subject subject) {
        return subjectService.updateSubject(subject);
    }

    @DeleteMapping()
    void removeSubject(long id) {
         subjectService.removeSubject(id);
    }

    @GetMapping(path = "/{id}")
    Subject getSubjectById(@PathVariable(value = "id") Long id) {
        Subject subject = subjectService.getSubjectById(id);
        return subject;
    }

    @GetMapping()
    List<Subject> getList() {
        return subjectService.getList();
    }

    @GetMapping(path = "/page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", paramType = "query", value = "1"),
            @ApiImplicitParam(name = "size", paramType = "query", value = "10")
    })
    Page<Subject> getPageList(@ApiIgnore @PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC)
                                          Pageable pageable) {
        Page<Subject> subjectPage = subjectService.getPageList(pageable);
        return subjectPage;
    }
}
