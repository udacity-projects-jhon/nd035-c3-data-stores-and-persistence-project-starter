package com.udacity.jdnd.course3.critter.schedule.service;

import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ScheduleService {

    ScheduleDTO createSchedule(ScheduleDTO scheduleDTO);

    List<ScheduleDTO> getAllSchedules();

    List<ScheduleDTO> getScheduleForPet(@PathVariable long petId);

    List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId);

    List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId);
}
