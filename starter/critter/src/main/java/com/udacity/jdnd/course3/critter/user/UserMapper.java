package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.user.model.Customer;
import com.udacity.jdnd.course3.critter.user.model.DayOfWeekEntity;
import com.udacity.jdnd.course3.critter.user.model.Employee;
import com.udacity.jdnd.course3.critter.user.model.EmployeeSkillEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.DayOfWeek;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    // TODO: list pets
    Customer customerDTOToCustomer(CustomerDTO customerDTO);

    // TODO: list pets
    CustomerDTO customerToCustomerDTO(Customer customer);

    default Employee employeeDTOToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();

        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        Set<EmployeeSkillEntity> skillEntities = employeeDTO.getSkills().stream()
                .map(employeeSkill -> {
                    EmployeeSkillEntity employeeSkillEntity = new EmployeeSkillEntity();
                    employeeSkillEntity.setEmployee(employee);
                    employeeSkillEntity.setEmployeeSkill(employeeSkill);

                    return employeeSkillEntity;
                }).collect(Collectors.toSet());

        employee.setSkills(skillEntities);

        Set<DayOfWeekEntity> dayOfWeekEntities = daysOfWeekToDayOfWeekEntity(employeeDTO.getDaysAvailable(), employee);

        employee.setDaysAvailable(dayOfWeekEntities);

        return employee;
    }

    default EmployeeDTO employeeToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setName(employeeDTO.getName());
        employeeDTO.setId(employee.getId());

        Set<EmployeeSkill> employeeSkills = employee.getSkills().stream()
                .map(EmployeeSkillEntity::getEmployeeSkill)
                .collect(Collectors.toSet());

        employeeDTO.setSkills(employeeSkills);

        Set<DayOfWeek> dayOfWeeks = daysOfWeekEntityToDayOfWeek(employee);

        employeeDTO.setDaysAvailable(dayOfWeeks);

        return employeeDTO;
    }

    default Set<DayOfWeek> daysOfWeekEntityToDayOfWeek(Employee employee) {
        return employee.getDaysAvailable().stream()
                .map(DayOfWeekEntity::getDayOfWeek)
                .collect(Collectors.toSet());
    }

    default Set<DayOfWeekEntity> daysOfWeekToDayOfWeekEntity(Set<DayOfWeek> dayOfWeeks, Employee employee) {
        return dayOfWeeks.stream()
                .map(dayOfWeek -> {
                    DayOfWeekEntity dayOfWeekEntity = new DayOfWeekEntity();
                    dayOfWeekEntity.setEmployee(employee);
                    dayOfWeekEntity.setDayOfWeek(dayOfWeek);

                    return dayOfWeekEntity;
                }).collect(Collectors.toSet());
    }
}
