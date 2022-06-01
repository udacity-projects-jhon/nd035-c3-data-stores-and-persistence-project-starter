package com.udacity.jdnd.course3.critter.user.service;

import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAll();

    CustomerDTO save(CustomerDTO customerDTO);

    CustomerDTO getOwnerByPet(@PathVariable long petId);
}
