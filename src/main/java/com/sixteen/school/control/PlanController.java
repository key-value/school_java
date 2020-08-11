package com.sixteen.school.control;

import com.sixteen.school.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/plan")
public class PlanController {

    @Autowired
    PlanService planService;

    @PostMapping(name = "/new")
    public Object getNewPlat(){
        return planService.play();
    }
}
