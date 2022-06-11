package com.udacity.jdnd.course3.critter.schedule.model;

import com.udacity.jdnd.course3.critter.pet.model.Pet;
import com.udacity.jdnd.course3.critter.user.model.Employee;
import com.udacity.jdnd.course3.critter.user.model.EmployeeSkillEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Schedule {
    @Id
    @Column(nullable = false)
    private long id;

    @OneToMany
    private List<Employee> employees;

    @OneToMany
    private List<Pet> pets;

    private LocalDate date;

    @OneToMany
    private Set<EmployeeSkillEntity> activities;
}
