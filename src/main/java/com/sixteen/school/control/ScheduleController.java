package com.sixteen.school.control;

import com.sixteen.school.model.Schedule;
import com.sixteen.school.result.QueryResult;
import com.sixteen.school.services.ScheduleService;
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
@RequestMapping(value = "v1/schedule")
public class ScheduleController {

    @Autowired
    public ScheduleService scheduleService;

    @PostMapping()
    Schedule add(@RequestBody Schedule schedule) {
        return scheduleService.addSchedule(schedule);
    }

    @PutMapping(path = "/{id}")
    Schedule update(@RequestBody Schedule schedule,@PathVariable(value = "id") Schedule oldSchedule) {
        oldSchedule.setGlassId(schedule.getGlassId());
        oldSchedule.setCount(schedule.getCount());
        oldSchedule.setSubjectId(schedule.getSubjectId());
        return scheduleService.updateSchedule(oldSchedule);
    }

    @DeleteMapping(path = "/{id}")
    void remove(long id) {
         scheduleService.removeSchedule(id);
    }

    @GetMapping(path = "/{id}")
    Schedule getById(@PathVariable(value = "id") Long id) {
        Schedule schedule = scheduleService.getScheduleById(id);
        return schedule;
    }

    @GetMapping()
    List<Schedule> getList() {
        return scheduleService.getList();
    }

    @GetMapping(path = "/page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", paramType = "query", value = "1"),
            @ApiImplicitParam(name = "size", paramType = "query", value = "10")
    })
    QueryResult<Schedule> getPageList(@ApiIgnore @PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC)
                                           Pageable pageable) {
        Page<Schedule> subjectPage = scheduleService.getPageList(pageable);
        return new QueryResult<>(subjectPage);
    }


}
