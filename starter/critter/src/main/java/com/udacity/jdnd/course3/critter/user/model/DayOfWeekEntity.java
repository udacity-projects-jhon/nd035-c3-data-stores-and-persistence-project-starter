package com.udacity.jdnd.course3.critter.user.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.DayOfWeek;

@Getter
@Setter
@Entity
public class DayOfWeekEntity {
    @Id
    @GeneratedValue
    private long id;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @ManyToOne(cascade = CascadeType.ALL)
    private Employee employee;
}
