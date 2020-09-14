package com.sixteen.school.control;

import com.sixteen.school.model.Subject;
import com.sixteen.school.result.QueryResult;
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
    Subject addSubject( @RequestBody Subject otherSubject) {
        Subject subject = new Subject();
        subject.setSubjectName(otherSubject.getSubjectName());
        return subjectService.addSubject(subject);
    }

    @PutMapping(path = "/{id}")
    Subject updateSubject(@PathVariable(value = "id")Subject oldSubject,@RequestBody Subject subject) {
        oldSubject.setSubjectName(subject.getSubjectName());
        return subjectService.updateSubject(subject);
    }

    @DeleteMapping(path = "/{id}")
    void removeSubject(long id) {
         subjectService.removeSubject(id);
    }

    @GetMapping(path = "/{id}")
    Subject getSubjectById(@PathVariable(value = "id") Subject oldSubject) {
        return oldSubject;
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
    QueryResult<Subject> getPageList(@ApiIgnore @PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC)
                                          Pageable pageable) {
        Page<Subject> subjectPage = subjectService.getPageList(pageable);
        return new QueryResult<>(subjectPage);
    }
}
