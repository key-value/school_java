package com.sixteen.school.control;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/plan")
public class PlanController {

    @PostMapping(name = "/new")
    public void getNewPlat(){

    }
}
