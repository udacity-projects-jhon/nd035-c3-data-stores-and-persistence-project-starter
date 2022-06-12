package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.model.Pet;
import com.udacity.jdnd.course3.critter.schedule.model.Schedule;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.model.Employee;
import com.udacity.jdnd.course3.critter.user.model.EmployeeSkillEntity;
import com.udacity.jdnd.course3.critter.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface ScheduleMapper {

    ScheduleMapper SCHEDULE_MAPPER = Mappers.getMapper(ScheduleMapper.class);

    default Schedule scheduleDTOToSchedule(ScheduleDTO scheduleDTO,
                                           List<Employee> employees,
                                           List<Pet> pets) {

        Schedule schedule = new Schedule();
        schedule.setDate(scheduleDTO.getDate());
        schedule.setEmployees(employees);
        schedule.setPets(pets);


        Set<EmployeeSkillEntity> activities = scheduleDTO.getActivities().stream()
                .map(employeeSkill -> {
                    EmployeeSkillEntity employeeSkillEntity = new EmployeeSkillEntity();
                    employeeSkillEntity.setEmployeeSkill(employeeSkill);
                    return employeeSkillEntity;
                })
                .collect(Collectors.toSet());

        schedule.setActivities(activities);

        return schedule;
    }

    default ScheduleDTO scheduleToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setDate(schedule.getDate());
        schedule.setId(schedule.getId());
        List<Long> employeeIds = schedule.getEmployees().stream()
                .map(User::getId)
                .collect(Collectors.toList());

        scheduleDTO.setEmployeeIds(employeeIds);

        Set<EmployeeSkill> employeeSkills = schedule.getActivities().stream()
                .map(EmployeeSkillEntity::getEmployeeSkill)
                .collect(Collectors.toSet());

        scheduleDTO.setActivities(employeeSkills);

        List<Long> petIds = schedule.getPets().stream()
                .map(Pet::getId)
                .collect(Collectors.toList());

        scheduleDTO.setPetIds(petIds);

        return scheduleDTO;
    }
}
