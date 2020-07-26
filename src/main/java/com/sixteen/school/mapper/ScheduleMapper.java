package com.sixteen.school.mapper;

import com.sixteen.school.model.Schedule;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component
@org.apache.ibatis.annotations.Mapper
public interface ScheduleMapper extends Mapper<Schedule> {
}
