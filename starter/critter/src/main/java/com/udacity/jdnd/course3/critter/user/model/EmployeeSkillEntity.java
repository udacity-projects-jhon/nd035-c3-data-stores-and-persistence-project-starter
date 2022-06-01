package com.udacity.jdnd.course3.critter.user.model;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class EmployeeSkillEntity {
    @Id
    @GeneratedValue
    private long id;

    @Enumerated(EnumType.STRING)
    private EmployeeSkill employeeSkill;

    @ManyToOne
    private Employee employee;
}
