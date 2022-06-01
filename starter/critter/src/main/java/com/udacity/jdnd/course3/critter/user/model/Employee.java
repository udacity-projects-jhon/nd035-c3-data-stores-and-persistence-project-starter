package com.udacity.jdnd.course3.critter.user.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.Set;

@Getter
@Setter
@Entity
public class Employee extends User {

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<EmployeeSkillEntity> skills = Collections.emptySet();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<DayOfWeekEntity> daysAvailable = Collections.emptySet();
}
