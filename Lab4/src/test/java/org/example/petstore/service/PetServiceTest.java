package org.example.petstore.service;

import org.example.petstore.model.Pet;
import org.example.petstore.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetService petService;

    @Test
    public void testAddPet() {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("bomboclat");

        when(petRepository.save(pet)).thenReturn(pet);

        Pet savedPet = petService.addPet(pet);

        assertEquals(pet, savedPet);
        verify(petRepository, times(1)).save(pet);
    }

    @Test
    public void testGetPetById() {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("bomboclat");

        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));

        Optional<Pet> foundPet = petService.getPetById(1L);

        assertEquals(pet, foundPet.get());
        verify(petRepository, times(1)).findById(1L);
    }
}