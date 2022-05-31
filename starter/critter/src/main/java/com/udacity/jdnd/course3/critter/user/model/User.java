package com.udacity.jdnd.course3.critter.user.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public abstract class User {
    @Id
    @GeneratedValue
    protected long id;

    protected String name;
}
