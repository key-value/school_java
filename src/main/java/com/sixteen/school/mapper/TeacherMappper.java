package com.sixteen.school.mapper;

import com.sixteen.school.model.Teacher;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Component
public interface TeacherMappper  extends Mapper<Teacher> {
//    @Insert("")
//    long addTeacher(long Id);
//    Teacher updateTeacher();
//    @Delete("delete from Teacher where id = #{id}")
//    long removeTeacher(long id);
//    @Select("select * from Teacher where id = #{id}")
//    Teacher getTeacherById(long id);

}
