package com.sixteen.school.control;

import com.sixteen.school.model.Glass;
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

@RestController
@RequestMapping(value = "v1/glass")
public class GlassController {

    @Autowired
    public GlassService glassService;

    @PostMapping()
    Glass add(Glass glass) {
        return glassService.addGlass(glass);
    }

    @PutMapping()
    Glass update(Glass glass) {
        return glassService.updateGlass(glass);
    }

    @DeleteMapping()
    void remove(long id) {
        glassService.removeGlass(id);
    }

    @GetMapping(path = "/{id}")
    Glass getById(@PathVariable(value = "id") Long id) {
        Glass glass = glassService.getGlassById(id);
        return glass;
    }

    @GetMapping()
    List<Glass> getList() {
        return glassService.getList();
    }

    @GetMapping(path = "/page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", paramType = "query", value = "1"),
            @ApiImplicitParam(name = "size", paramType = "query", value = "10")
    })
    Page<Glass>  getPageList(@ApiIgnore @PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC)
                                          Pageable pageable) {
        Page<Glass> glasses = glassService.getPageList(    pageable);
        return glasses;
    }
}
