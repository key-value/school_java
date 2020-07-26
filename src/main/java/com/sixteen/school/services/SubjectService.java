package com.sixteen.school.services;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sixteen.school.mapper.SubjectMapper;
import com.sixteen.school.model.Schedule;
import com.sixteen.school.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    public SubjectMapper subjectMapper;

    public long addGlass(Subject schedule) {
        return subjectMapper.insert(schedule);
    }

    public int updateGlass(Subject schedule) {
        return subjectMapper.updateByPrimaryKeySelective(schedule);
    }

    public int removeGlass(long id) {
        Schedule schedule = new Schedule();
        schedule.setId(id);
        return subjectMapper.deleteByExample(schedule);
    }

    public Subject getGlassById(Long  id) {
        return subjectMapper.selectByPrimaryKey(id);
    }

    public List<Subject> getList(){
        return subjectMapper.selectAll();
    }
    public PageInfo<Subject> getPageList(int pageSize, int pageIndex){
        PageHelper.startPage(pageIndex, pageSize);
        List<Subject> glassList= subjectMapper.selectAll();
        PageInfo info=new PageInfo(glassList);
        return info ;
    }
}
