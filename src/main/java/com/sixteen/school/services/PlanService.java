package com.sixteen.school.services;

import com.sixteen.school.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PlanService {


    @Autowired
    public ScheduleService scheduleService;

    public void play(){
        List<Schedule> scheduleList = scheduleService.getList();

    }
}
