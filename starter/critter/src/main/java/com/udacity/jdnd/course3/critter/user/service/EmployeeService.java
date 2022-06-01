package com.udacity.jdnd.course3.critter.user.service;

import com.udacity.jdnd.course3.critter.user.EmployeeDTO;

public interface EmployeeService {
    EmployeeDTO save(EmployeeDTO employeeDTO);

    EmployeeDTO getById(long employeeId);
}
