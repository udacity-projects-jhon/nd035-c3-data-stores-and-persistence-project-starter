package com.udacity.jdnd.course3.critter.user.service.imp;

import com.udacity.jdnd.course3.critter.exception.NotFound;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.model.Employee;
import com.udacity.jdnd.course3.critter.user.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.udacity.jdnd.course3.critter.user.UserMapper.USER_MAPPER;

@Service
public class EmployeeServiceImp implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImp(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employee = USER_MAPPER.employeeDTOToEmployee(employeeDTO);
        Employee employeeSaved = employeeRepository.save(employee);
        return USER_MAPPER.employeeToEmployeeDTO(employeeSaved);
    }

    @Override
    @Transactional
    public EmployeeDTO getById(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(NotFound::new);

        return USER_MAPPER.employeeToEmployeeDTO(employee);
    }

    @Override
    @Transactional
    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(NotFound::new);

        employee.setDaysAvailable(USER_MAPPER.daysOfWeekToDayOfWeekEntity(daysAvailable, employee));

        employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
        DayOfWeek dayOfWeek = employeeDTO.getDate().getDayOfWeek();
        return employeeRepository.findAllByDaysAvailableAndInSkills(dayOfWeek,
                        employeeDTO.getSkills(), employeeDTO.getSkills().size()
                )
                .stream()
                .map(USER_MAPPER::employeeToEmployeeDTO)
                .collect(Collectors.toList());
    }
}
