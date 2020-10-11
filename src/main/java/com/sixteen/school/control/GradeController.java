package com.sixteen.school.control;


import com.sixteen.school.model.Grade;
import com.sixteen.school.result.QueryResult;
import com.sixteen.school.services.GradeService;
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
@RequestMapping(value = "v1/grade")
public class GradeController {

    @Autowired
    public GradeService gradeService;

    @PostMapping()
    Grade add(@RequestBody Grade grade) {
        return gradeService.addGrade(grade);
    }

    @PutMapping(path = "/{id}")
    Grade update(@PathVariable(value = "id")  Grade oldGrade,@RequestBody  Grade grade ) {
        oldGrade.setGradeNum(grade.getGradeNum());
        oldGrade.setSign(grade.getSign());
        return gradeService.updateGrade(oldGrade);
    }

    @DeleteMapping(path = "/{id}")
    void remove(long id) {
        gradeService.removeGrade(id);
    }

    @GetMapping(path = "/{id}")
    Grade getById(@PathVariable(value = "id") Grade grade) {
        return grade;
    }

    @GetMapping()
    List<Grade> getList() {
        return gradeService.getList();
    }

    @GetMapping(path = "/page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", paramType = "query", value = "1"),
            @ApiImplicitParam(name = "size", paramType = "query", value = "10")
    })
    QueryResult<Grade> getPageList(@ApiIgnore @PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC)
                                           Pageable pageable) {
        Page<Grade> grades = gradeService.getPageList(    pageable);
        return new QueryResult<>(grades);
    }
}
