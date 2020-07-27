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

    public long addSubject(Subject subject) {
        return subjectMapper.insert(subject);
    }

    public int updateSubject(Subject subject) {
        return subjectMapper.updateByPrimaryKeySelective(subject);
    }

    public int removeSubject(long id) {
        Schedule subject = new Schedule();
        subject.setId(id);
        return subjectMapper.deleteByExample(subject);
    }

    public Subject getSubjectById(Long  id) {
        return subjectMapper.selectByPrimaryKey(id);
    }

    public List<Subject> getList(){
        return subjectMapper.selectAll();
    }
    public PageInfo<Subject> getPageList(int pageSize, int pageIndex){
        PageHelper.startPage(pageIndex, pageSize);
        List<Subject> subjectList= subjectMapper.selectAll();
        PageInfo info=new PageInfo(subjectList);
        return info ;
    }
}
