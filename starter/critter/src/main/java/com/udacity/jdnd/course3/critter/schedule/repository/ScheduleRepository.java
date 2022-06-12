package com.udacity.jdnd.course3.critter.schedule.repository;

import com.udacity.jdnd.course3.critter.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s from Schedule s " +
            "join FETCH s.employees em " +
            "where em.id = :employeeId")
    List<Schedule> getAllForEmployee(@Param("employeeId") long employeeId);

    @Query("SELECT s from Schedule s " +
            "join FETCH s.pets pets " +
            "where pets.id = :petId")
    List<Schedule> getAllForPet(@Param("petId") long petId);

    @Query("SELECT s from Schedule s " +
            "join FETCH s.pets pets " +
            "where pets.customer.id = :customerId")
    List<Schedule> getAllForCustomer(@Param("customerId") long customerId);

}
