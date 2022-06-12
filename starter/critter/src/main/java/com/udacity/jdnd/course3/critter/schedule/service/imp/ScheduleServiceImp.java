package com.udacity.jdnd.course3.critter.schedule.service.imp;

import com.udacity.jdnd.course3.critter.pet.model.Pet;
import com.udacity.jdnd.course3.critter.pet.repository.PetRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.schedule.model.Schedule;
import com.udacity.jdnd.course3.critter.schedule.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.service.ScheduleService;
import com.udacity.jdnd.course3.critter.user.model.Employee;
import com.udacity.jdnd.course3.critter.user.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

import static com.udacity.jdnd.course3.critter.schedule.ScheduleMapper.SCHEDULE_MAPPER;

@Service
public class ScheduleServiceImp implements ScheduleService {
    private final EmployeeRepository employeeRepository;
    private final PetRepository petRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImp(EmployeeRepository employeeRepository, PetRepository petRepository, ScheduleRepository scheduleRepository) {
        this.employeeRepository = employeeRepository;
        this.petRepository = petRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
        List<Employee> employees = employeeRepository.findAllById(scheduleDTO.getEmployeeIds());

        List<Pet> pets = petRepository.findAllById(scheduleDTO.getPetIds());

        Schedule schedule = SCHEDULE_MAPPER.scheduleDTOToSchedule(scheduleDTO, employees, pets);

        Schedule scheduleSaved = scheduleRepository.save(schedule);

        employees.forEach(employee -> {
            employee.getSchedules().add(scheduleSaved);
            employeeRepository.save(employee);
        });

        pets.forEach(pet -> {
            pet.getSchedules().add(scheduleSaved);
            petRepository.save(pet);
        });

        return SCHEDULE_MAPPER.scheduleToScheduleDTO(scheduleSaved);
    }

    @Override
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleRepository.findAll().stream().map(SCHEDULE_MAPPER::scheduleToScheduleDTO).collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return scheduleRepository.getAllForPet(petId).stream().map(SCHEDULE_MAPPER::scheduleToScheduleDTO).collect(Collectors.toList());

    }

    @Override
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {

        return scheduleRepository.getAllForEmployee(employeeId).stream().map(SCHEDULE_MAPPER::scheduleToScheduleDTO).collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleRepository.getAllForCustomer(customerId).stream().map(SCHEDULE_MAPPER::scheduleToScheduleDTO).collect(Collectors.toList());
    }
}
