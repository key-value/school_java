package com.sixteen.school.services;

import com.sixteen.school.model.Schedule;
import com.sixteen.school.model.Teacher;
import com.sixteen.school.star.scan.MultiService;
import com.sixteen.school.star.scan.MultiSign;

@MultiSign
public interface GlassScheduleService extends MultiService<Teacher, Schedule> {
}
