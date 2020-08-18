package com.sixteen.school.services;
import com.google.common.collect.Lists;

import com.sixteen.school.dto.Plan;
import com.sixteen.school.model.Schedule;
import com.sixteen.school.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlanService {

    @Autowired
    public ScheduleService scheduleService;
@Autowired
    public SubjectService subjectService;

    private Long[][] table;

    private int dayNum = 5;

    private int daySize = 7;


    public Map<Long, List<Plan>> play() {
        table = new Long[dayNum][daySize + 1];
        List<Plan> scheduleList =toPlan( scheduleService.getList());
        List<Long> glassList = scheduleList.stream().map(Plan::getGlassId).distinct().collect(Collectors.toList());
        Map<Long, List<Plan>> plan = init(glassList, 5, 7);
        do {
            boolean any = forSchedule(plan, scheduleList);
            if (!any) {
                Plan schedule = scheduleList.get(0);
                System.out.print(schedule.getId() + "(" + schedule.getTeacherId() + "," + schedule.getSubjectId() + "),");
                scheduleList.remove(0);
            }
            scheduleList = scheduleList.stream().filter(schedule -> schedule.getCount() > 0).sorted(Comparator.comparing(Plan::getSort).reversed()).collect(Collectors.toList());
        } while (scheduleList.size() > 0);
        showResult(plan);
        return plan;
    }

    private boolean forSchedule(Map<Long, List<Plan>> plan, List<Plan> scheduleList) {
        boolean any = false;
        for (Plan schedule : scheduleList) {
            if (scheduleOneSubject(plan, schedule)) {
                any = true;
                break;
            }
        }
        return any;
    }

    private boolean scheduleOneSubject(Map<Long, List<Plan>> plan, Plan schedule) {
        if (schedule.getCount() <= 0) {
            return false;
        }
        int scheduleIndex = plan.get(schedule.getGlassId()).lastIndexOf(schedule);
        if (scheduleIndex >= 0) {
            int nextday = (scheduleIndex + daySize ) / daySize;
            if (nextday <= dayNum) {
                if (OptimizationSchedule(plan, schedule, nextday * daySize )) {
                    return true;
                }
            }
        }
        return OptimizationSchedule(plan, schedule, 0);
    }

    private boolean OptimizationSchedule(Map<Long, List<Plan>> plan, Plan schedule, int beginIndex) {
        int index = beginIndex;
        List<Plan> glassPlan = plan.get(schedule.getGlassId());
        List<Plan> schedules = glassPlan.subList(beginIndex, glassPlan.size());
        for (Plan schedule1 : schedules) {
            if (schedule1.getId() > 0) {
                index++;
                continue;
            }
            if (filterOtherGlass(plan, schedule, index)) {
                index++;
                continue;
            }
            glassPlan.set(index, schedule);
            schedule.setCount(schedule.getCount() - 1);
            return true;
        }
        return false;
    }

    private boolean filterOtherGlass(Map<Long, List<Plan>> plan, Plan schedule, int i) {
        for (Map.Entry<Long, List<Plan>> longListEntry : plan.entrySet()) {
            if (longListEntry.getKey() == schedule.getGlassId()) {
                continue;
            }
            Plan otherSchedule = longListEntry.getValue().get(i);
            if (otherSchedule.getId() != 0 && otherSchedule.getTeacherId() == schedule.getTeacherId()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private Map<Long, List<Plan>> init(List<Long> glassList, int day, int number) {
        Map<Long, List<Plan>> planMap = new HashMap<>();
        for (Long glassId : glassList) {
            List<Plan> planList = new ArrayList<>();
            for (int j = 0; j < day * number; j++) {
                Plan schedule = new Plan();
                schedule.setId(0L);
                planList.add(schedule);
            }
            planMap.put(glassId, planList);
        }
        return planMap;
    }

    private void showResult(Map<Long, List<Plan>> plan) {
        for (Map.Entry<Long, List<Plan>> longListEntry : plan.entrySet()) {
            System.out.println(longListEntry.getKey());
            int index = 0;
            for (Plan schedule : longListEntry.getValue()) {
                if (index % 7 == 0) {
                    System.out.println();
                }
                index += 1;
                System.out.print(schedule.getId() + "(" + schedule.getTeacherId() + "," + schedule.getSubjectName() + "),");
            }
            System.out.println();
        }
        System.out.println();
    }

    private List<Plan> toPlan(List<Schedule> scheduleList){
        List<Subject> list = subjectService.getList();
        Map<Long, Subject> subjectMap = list.stream().collect(Collectors.toMap(subject -> subject.getId(), subject -> subject));

        List<Plan> planlist=Lists.newArrayList();
        for (Schedule schedule :scheduleList) {
        	planlist.add(toPlan(schedule,subjectMap.get(schedule.getSubjectId())));
        }
        return planlist;

    }
    private Plan toPlan(Schedule schedule, Subject subject){
        Plan plan = new Plan();
        plan.setId(schedule.getId());
        plan.setTeacherId(schedule.getTeacherId());
        plan.setGlassId(schedule.getGlassId());
        plan.setSubjectId(schedule.getSubjectId());
        plan.setCount(schedule.getCount());
        plan.setSort(subject.getSort());
        plan.setSubjectName(subject.getSubjectName());
        return plan;

    }
}
