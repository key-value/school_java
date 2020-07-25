package com.sixteen.school.services;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sixteen.school.mapper.TeacherMappper;
import com.sixteen.school.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    public TeacherMappper teacherMappper;

    public long addTeacher(Teacher teacher) {
        return teacherMappper.insert(teacher);
    }

    public int updateTeacher(Teacher teacher) {
        return teacherMappper.updateByPrimaryKeySelective(teacher);
    }

    public int removeTeacher(long id) {
        Teacher teacher = new Teacher();
        teacher.setId(id);
        return teacherMappper.deleteByExample(teacher);
    }

    public Teacher getTeacherById(Long  id) {
        return teacherMappper.selectByPrimaryKey(id);
    }

    public List<Teacher> getList(){
        return teacherMappper.selectAll();
    }
    public PageInfo<Teacher> getPageList(int pageSize,int pageIndex){
        PageHelper.startPage(pageIndex, pageSize);
        List<Teacher> teacherList= teacherMappper.selectAll();
        PageInfo info=new PageInfo(teacherList);
        System.out.println(info.getTotal());//总条数
        System.out.println(info.getList());//显示的数据
        return info ;
    }
}
