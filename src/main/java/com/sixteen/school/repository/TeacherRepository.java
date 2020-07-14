package com.sixteen.school.repository;

import com.sixteen.school.model.Teacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

public interface TeacherRepository {
    long addTeacher(long Id);
    Teacher updateTeacher();
    @Delete("delete from Teacher where id = #{id}")
    long removeTeacher(long id);
    @Select("select * from Teacher where id = #{id}")
    Teacher getTeacherById(long id);

}
