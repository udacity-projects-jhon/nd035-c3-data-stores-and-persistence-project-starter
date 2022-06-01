package com.udacity.jdnd.course3.critter.user.service.imp;

import com.udacity.jdnd.course3.critter.exception.NotFound;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.model.Employee;
import com.udacity.jdnd.course3.critter.user.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.Set;

import static com.udacity.jdnd.course3.critter.user.UserMapper.USER_MAPPER;

@Service
public class EmployeeServiceImp implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImp(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employee = USER_MAPPER.employeeDTOToEmployee(employeeDTO);
        Employee employeeSaved = employeeRepository.save(employee);
        return USER_MAPPER.employeeToEmployeeDTO(employeeSaved);
    }

    @Override
    public EmployeeDTO getById(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(NotFound::new);

        return USER_MAPPER.employeeToEmployeeDTO(employee);
    }

    @Override
    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(NotFound::new);

        employee.setDaysAvailable(USER_MAPPER.daysOfWeekToDayOfWeekEntity(daysAvailable, employee));

        employeeRepository.save(employee);
    }
}
