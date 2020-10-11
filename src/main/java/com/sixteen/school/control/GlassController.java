package com.sixteen.school.control;

import com.sixteen.school.model.Glass;
import com.sixteen.school.model.Grade;
import com.sixteen.school.result.QueryResult;
import com.sixteen.school.services.GlassService;
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
import java.util.Map;

@RestController
@RequestMapping(value = "v1/glass")
public class GlassController {


    @Autowired
    public GlassService glassService;

    @PostMapping()
    Glass add(@RequestBody Glass glass) {
        return glassService.add(glass);
    }

    @PutMapping(path = "/{id}")
    Glass update(@RequestBody Glass glass) {
        return glassService.update(glass);
    }

    @DeleteMapping(path = "/{id}")
    void remove(long id) {
        glassService.remove(id);
    }

    @GetMapping(path = "/{id}")
    Glass getById(@PathVariable(value = "id") Long id) {
        Glass glass = glassService.getById(id);
        return glass;
    }

    @GetMapping(path = "/grade")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", paramType = "query", value = "1"),
            @ApiImplicitParam(name = "size", paramType = "query", value = "10")
    })
    QueryResult<Glass>  getGlassList(@ApiIgnore @PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC)   Pageable pageable) {
        Page<Glass> glasses = glassService.getPageList(pageable);
        Map<Long, Grade> gradeMap = glassService.getGradeMap(glasses.getContent());
        QueryResult<Glass> glassQueryResult = new QueryResult<>(glasses);
        glassQueryResult.add("grade",gradeMap);
        return glassQueryResult;
    }

    @GetMapping()
    List<Glass> getList()
    {
        glassService.test();
        return glassService.getList();
    }

    @GetMapping(path = "/page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", paramType = "query", value = "1"),
            @ApiImplicitParam(name = "size", paramType = "query", value = "10")
    })
    QueryResult<Glass> getPageList(@ApiIgnore @PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC)
                                          Pageable pageable) {
        Page<Glass> glasses = glassService.getPageList(pageable);
        return new QueryResult<>(glasses);
    }
}
