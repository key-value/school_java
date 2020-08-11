package com.sixteen.school.services;

import com.sixteen.school.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanService {


    @Autowired
    public ScheduleService scheduleService;

    public Schedule[][][] play() {
        Schedule[][][] plan = new Schedule[2][5][7];
        List<Schedule> scheduleList = scheduleService.getList();
        do {
            scheduleList = forSchedule(plan, scheduleList);
        } while (scheduleList.size() > 0);
        showResult(plan);
        return plan;
    }

    private List<Schedule> forSchedule(Schedule[][][] plan, List<Schedule> scheduleList) {
        for (Schedule schedule : scheduleList) {
            scheduleOneSubject(plan, schedule);
        }
        return scheduleList.stream().filter(schedule -> schedule.getCount() > 0).collect(Collectors.toList());
    }

    private void scheduleOneSubject(Schedule[][][] plan, Schedule schedule) {
        int glassId = schedule.getGlassId().intValue() - 1;
        for (int i = 0; i < plan[glassId].length; i++) {
            if (schedule.getCount() <= 0) {
                return;
            }
            for (int j = 0; j < plan[glassId][i].length; j++) {
                Schedule schedule1 = plan[glassId][i][j];
                if (schedule1 != null) {
                    continue;
                }
                if (filterOtherGlass(plan, schedule, i, j)) {
                    continue;
                }

                plan[glassId][i][j] = schedule;
                schedule.setCount(schedule.getCount() - 1);
                break;
            }

        }
    }

    private boolean filterOtherGlass(Schedule[][][] plan, Schedule schedule, int i, int j) {
        for (int otherglassId = 0; otherglassId < plan.length; otherglassId++) {
            if (otherglassId == schedule.getGlassId()) {
                return false;
            }
            if (plan[otherglassId][i][j] != null && plan[otherglassId][i][j].getTeacherId() == schedule.getTeacherId()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private void showResult(Schedule[][][] plan) {
        for (int index = 0; index < plan.length; index++) {
            System.out.println(index);
            for (int i = 0; i < plan[index].length; i++) {
                for (int j = 0; j < plan[index][i].length; j++) {
                    Schedule schedule = plan[index][i][j];
                    if (schedule == null) {
                        System.out.print(0);
                        continue;
                    }
                    System.out.print(schedule.getId() + "(" + schedule.getTeacherId() + "," + schedule.getSubjectId() + "),");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
