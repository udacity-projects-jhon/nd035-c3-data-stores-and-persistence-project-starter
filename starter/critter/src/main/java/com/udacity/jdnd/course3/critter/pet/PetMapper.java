package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.pet.model.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PetMapper {

    PetMapper PET_MAPPER = Mappers.getMapper(PetMapper.class);

    Pet petDTOtoPet(PetDTO petDTO);

    PetDTO petToPetDTO(Pet pet);
}
