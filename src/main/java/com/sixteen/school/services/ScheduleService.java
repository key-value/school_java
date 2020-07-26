package com.sixteen.school.services;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sixteen.school.mapper.ScheduleMapper;
import com.sixteen.school.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    public ScheduleMapper scheduleMapper;

    public long addGlass(Schedule schedule) {
        return scheduleMapper.insert(schedule);
    }

    public int updateGlass(Schedule schedule) {
        return scheduleMapper.updateByPrimaryKeySelective(schedule);
    }

    public int removeGlass(long id) {
        Schedule schedule = new Schedule();
        schedule.setId(id);
        return scheduleMapper.deleteByExample(schedule);
    }

    public Schedule getGlassById(Long  id) {
        return scheduleMapper.selectByPrimaryKey(id);
    }

    public List<Schedule> getList(){
        return scheduleMapper.selectAll();
    }
    public PageInfo<Schedule> getPageList(int pageSize, int pageIndex){
        PageHelper.startPage(pageIndex, pageSize);
        List<Schedule> glassList= scheduleMapper.selectAll();
        PageInfo info=new PageInfo(glassList);
        return info ;
    }
}
