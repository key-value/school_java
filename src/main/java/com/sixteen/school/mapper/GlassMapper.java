package com.sixteen.school.mapper;

import com.sixteen.school.model.Glass;
import tk.mybatis.mapper.common.Mapper;
import org.springframework.stereotype.Component;

@org.apache.ibatis.annotations.Mapper
@Component
public interface GlassMapper extends Mapper<Glass> {
}
