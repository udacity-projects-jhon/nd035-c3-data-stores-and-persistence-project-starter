package com.udacity.jdnd.course3.critter.user.service.imp;

import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.UserMapper;
import com.udacity.jdnd.course3.critter.user.model.Customer;
import com.udacity.jdnd.course3.critter.user.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImp implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAll() {
        return customerRepository.findAll().stream()
                .map(UserMapper.USER_MAPPER::customerToCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = UserMapper.USER_MAPPER.customerDTOToCustomer(customerDTO);
        Customer customerSaved = customerRepository.save(customer);
        return UserMapper.USER_MAPPER.customerToCustomerDTO(customerSaved);
    }
}
