package com.udacity.jdnd.course3.critter.pet.service;

import com.udacity.jdnd.course3.critter.pet.PetDTO;

import java.util.List;

public interface PetService {
    PetDTO save(PetDTO petDTO);

    PetDTO getById(long petId);

    List<PetDTO> getPetsByOwner(long ownerId);
}
