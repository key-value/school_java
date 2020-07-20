package com.sixteen.school.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="`name`")
    private String name;
}
