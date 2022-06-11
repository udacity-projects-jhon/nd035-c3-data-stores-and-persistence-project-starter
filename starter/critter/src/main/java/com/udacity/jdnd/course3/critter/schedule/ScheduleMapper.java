package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.model.Pet;
import com.udacity.jdnd.course3.critter.schedule.model.Schedule;
import com.udacity.jdnd.course3.critter.user.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

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

        return schedule;
    }
}
