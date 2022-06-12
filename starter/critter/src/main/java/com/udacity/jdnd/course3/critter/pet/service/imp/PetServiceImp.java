package com.udacity.jdnd.course3.critter.pet.service.imp;

import com.udacity.jdnd.course3.critter.exception.NotFound;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.pet.model.Pet;
import com.udacity.jdnd.course3.critter.pet.repository.PetRepository;
import com.udacity.jdnd.course3.critter.pet.service.PetService;
import com.udacity.jdnd.course3.critter.user.model.Customer;
import com.udacity.jdnd.course3.critter.user.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.udacity.jdnd.course3.critter.pet.PetMapper.PET_MAPPER;

@Service
public class PetServiceImp implements PetService {
    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public PetServiceImp(PetRepository petRepository, CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PetDTO save(PetDTO petDTO) {
        Pet pet = PET_MAPPER.petDTOtoPet(petDTO);
        Customer customer = customerRepository.findById(petDTO.getOwnerId())
                .orElseThrow(NotFound::new);

        customer.getPets().add(pet);

        Pet petSaved = petRepository.save(pet);
        pet.setCustomer(customer);

        PetDTO dtoToReturn = PET_MAPPER.petToPetDTO(petSaved);
        dtoToReturn.setOwnerId(petSaved.getCustomer().getId());
        return dtoToReturn;
    }

    @Override
    @Transactional
    public PetDTO getById(long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(NotFound::new);

        PetDTO dtoToReturn = PET_MAPPER.petToPetDTO(pet);
        dtoToReturn.setOwnerId(pet.getCustomer().getId());
        return dtoToReturn;
    }

    @Override
    @Transactional
    public List<PetDTO> getPetsByOwner(long ownerId) {
        return customerRepository.findById(ownerId)
                .orElseThrow(NotFound::new)
                .getPets()
                .stream()
                .map(PET_MAPPER::petToPetDTO)
                .peek(petDTO -> petDTO.setOwnerId(ownerId))
                .collect(Collectors.toList());
    }

}
