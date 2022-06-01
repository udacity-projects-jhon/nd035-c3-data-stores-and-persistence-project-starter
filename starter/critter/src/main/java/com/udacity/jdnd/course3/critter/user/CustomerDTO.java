package com.udacity.jdnd.course3.critter.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Collections;
import java.util.List;

/**
 * Represents the form that customer request and response data takes. Does not map
 * to the database directly.
 */
@Getter
@Setter
public class CustomerDTO {

    private long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String phoneNumber;

    private String notes;

    private List<Long> petIds = Collections.emptyList();
}
