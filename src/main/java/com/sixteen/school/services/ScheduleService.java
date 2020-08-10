package com.sixteen.school.services;

import com.sixteen.school.model.Schedule;
import com.sixteen.school.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    public Schedule addSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public Schedule updateSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public void removeSchedule(long id) {
         scheduleRepository.deleteById(id);
    }

    public Schedule getScheduleById(Long  id) {
        return scheduleRepository.findById(id).get();
    }

    public List<Schedule> getList(){
        return scheduleRepository.findAll();
    }
    public Page<Schedule> getPageList( Pageable pageable){
        Page<Schedule> scheduleList= scheduleRepository.findAll(pageable);
        return scheduleList ;
    }
}
