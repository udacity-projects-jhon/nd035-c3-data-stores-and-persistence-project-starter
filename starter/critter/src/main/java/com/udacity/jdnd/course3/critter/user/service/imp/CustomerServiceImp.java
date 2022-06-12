package com.udacity.jdnd.course3.critter.user.service.imp;

import com.udacity.jdnd.course3.critter.exception.NotFound;
import com.udacity.jdnd.course3.critter.pet.model.Pet;
import com.udacity.jdnd.course3.critter.pet.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.model.Customer;
import com.udacity.jdnd.course3.critter.user.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.udacity.jdnd.course3.critter.user.UserMapper.USER_MAPPER;

@Service
public class CustomerServiceImp implements CustomerService {
    private final CustomerRepository customerRepository;
    private final PetRepository petRepository;

    @Autowired
    public CustomerServiceImp(CustomerRepository customerRepository, PetRepository petRepository) {
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
    }

    @Override
    @Transactional
    public List<CustomerDTO> getAll() {
        return customerRepository.findAll().stream()
                .map(customer -> {
                    CustomerDTO customerDTO = USER_MAPPER.customerToCustomerDTO(customer);
                    customerDTO.setPetIds(customer.getPets().stream()
                            .map(Pet::getId)
                            .collect(Collectors.toList()));
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = USER_MAPPER.customerDTOToCustomer(customerDTO);
        Customer customerSaved = customerRepository.save(customer);
        return USER_MAPPER.customerToCustomerDTO(customerSaved);
    }

    @Override
    @Transactional
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        Customer customer = petRepository.findById(petId)
                .orElseThrow(NotFound::new)
                .getCustomer();


        CustomerDTO customerDTO = USER_MAPPER.customerToCustomerDTO(customer);
        customerDTO.setPetIds(customer.getPets().stream()
                .map(Pet::getId)
                .collect(Collectors.toList()));
        return customerDTO;

    }
}
