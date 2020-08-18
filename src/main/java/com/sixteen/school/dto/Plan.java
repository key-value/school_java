package com.sixteen.school.dto;

import lombok.Data;

@Data
public class Plan {
    private Long id;

    private Long teacherId;

    private Long glassId;

    private Long subjectId;

    private Integer count;

    private Integer Sort;

    private String subjectName;
}
