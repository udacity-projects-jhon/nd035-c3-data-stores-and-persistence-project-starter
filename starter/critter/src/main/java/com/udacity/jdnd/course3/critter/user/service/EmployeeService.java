package com.udacity.jdnd.course3.critter.user.service;

import com.udacity.jdnd.course3.critter.user.EmployeeDTO;

import java.time.DayOfWeek;
import java.util.Set;

public interface EmployeeService {
    EmployeeDTO save(EmployeeDTO employeeDTO);

    EmployeeDTO getById(long employeeId);

    void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId);
}
