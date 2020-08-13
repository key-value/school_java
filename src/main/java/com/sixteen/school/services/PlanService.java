package com.sixteen.school.services;

import com.sixteen.school.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlanService {

    @Autowired
    public ScheduleService scheduleService;

    private Long[][] table;

    private int dayNum = 5;

    private int daySize = 7;


    public Map<Long, List<Schedule>> play() {
        table = new Long[dayNum][daySize + 1];
        List<Schedule> scheduleList = scheduleService.getList();
        List<Long> glassList = scheduleList.stream().map(Schedule::getGlassId).distinct().collect(Collectors.toList());
        Map<Long, List<Schedule>> plan = init(glassList, 5, 7);
        do {
            boolean any = forSchedule(plan, scheduleList);
            if (!any) {
                Schedule schedule = scheduleList.get(0);
                System.out.print(schedule.getId() + "(" + schedule.getTeacherId() + "," + schedule.getSubjectId() + "),");
                scheduleList.remove(0);
            }
            scheduleList = scheduleList.stream().filter(schedule -> schedule.getCount() > 0).sorted(Comparator.comparing(Schedule::getCount).reversed()).collect(Collectors.toList());
        } while (scheduleList.size() > 0);
        showResult(plan);
        return plan;
    }

    private boolean forSchedule(Map<Long, List<Schedule>> plan, List<Schedule> scheduleList) {
        boolean any = false;
        for (Schedule schedule : scheduleList) {
            if (scheduleOneSubject(plan, schedule)) {
                any = true;
                break;
            }
        }
        return any;
    }

    private boolean scheduleOneSubject(Map<Long, List<Schedule>> plan, Schedule schedule) {
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

    private boolean OptimizationSchedule(Map<Long, List<Schedule>> plan, Schedule schedule, int beginIndex) {
        int index = beginIndex;
        List<Schedule> glassPlan = plan.get(schedule.getGlassId());
        List<Schedule> schedules = glassPlan.subList(beginIndex, glassPlan.size());
        for (Schedule schedule1 : schedules) {
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

    private boolean filterOtherGlass(Map<Long, List<Schedule>> plan, Schedule schedule, int i) {
        for (Map.Entry<Long, List<Schedule>> longListEntry : plan.entrySet()) {
            if (longListEntry.getKey() == schedule.getGlassId()) {
                continue;
            }
            Schedule otherSchedule = longListEntry.getValue().get(i);
            if (otherSchedule.getId() != 0 && otherSchedule.getTeacherId() == schedule.getTeacherId()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private Map<Long, List<Schedule>> init(List<Long> glassList, int day, int number) {
        Map<Long, List<Schedule>> planMap = new HashMap<>();
        for (Long glassId : glassList) {
            List<Schedule> planList = new ArrayList<>();
            for (int j = 0; j < day * number; j++) {
                Schedule schedule = new Schedule();
                schedule.setId(0L);
                planList.add(schedule);
            }
            planMap.put(glassId, planList);
        }
        return planMap;
    }

    private void showResult(Map<Long, List<Schedule>> plan) {
        for (Map.Entry<Long, List<Schedule>> longListEntry : plan.entrySet()) {
            System.out.println(longListEntry.getKey());
            int index = 0;
            for (Schedule schedule : longListEntry.getValue()) {
                if (index % 7 == 0) {
                    System.out.println();
                }
                index += 1;
                System.out.print(schedule.getId() + "(" + schedule.getTeacherId() + "," + schedule.getSubjectId() + "),");
            }
            System.out.println();
        }
        System.out.println();
    }
}
