package com.sixteen.school.services;

import com.sixteen.school.model.Glass;
import com.sixteen.school.model.Schedule;
import com.sixteen.school.model.Subject;
import com.sixteen.school.model.Teacher;
import com.sixteen.school.repository.GlassRepository;
import com.sixteen.school.repository.ScheduleRepository;
import com.sixteen.school.repository.SubjectRepository;
import com.sixteen.school.repository.TeacherRepository;
import com.sixteen.school.star.UnifiedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    private ScheduleRepository scheduleRepository;

    private UnifiedService<Schedule> unifiedService;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.unifiedService = new UnifiedService<>(scheduleRepository);
        this.scheduleRepository = scheduleRepository;
    }

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private GlassRepository glassRepository;

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

    public Teacher getTeacher(Schedule schedule){
        return teacherRepository.findById(schedule.getTeacherId()).get();
    }
    public Subject getSubject(Schedule schedule){
        return subjectRepository.findById(schedule.getSubjectId()).get();
    }
    public Glass getGlass(Schedule schedule){
        return glassRepository.findById(schedule.getGlassId()).get();
    }
}
