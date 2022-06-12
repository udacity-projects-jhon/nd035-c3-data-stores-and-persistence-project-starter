package com.udacity.jdnd.course3.critter.pet.model;

import com.udacity.jdnd.course3.critter.pet.PetType;
import com.udacity.jdnd.course3.critter.schedule.model.Schedule;
import com.udacity.jdnd.course3.critter.user.model.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Entity
public class Pet {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private PetType type;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    private LocalDate birthDate;

    private String notes;

    @ManyToMany
    private List<Schedule> schedules = new LinkedList<>();
}
