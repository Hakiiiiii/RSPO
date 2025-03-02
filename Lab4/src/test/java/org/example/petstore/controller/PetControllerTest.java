package org.example.petstore.controller;

import org.example.petstore.model.Pet;
import org.example.petstore.service.PetService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PetControllerTest {

    @Mock
    private PetService petService;

    @InjectMocks
    private PetController petController;

    @Test
    public void testAddPet() {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("bomboclat");

        when(petService.addPet(pet)).thenReturn(pet);

        ResponseEntity<Pet> response = petController.addPet(pet);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(pet, response.getBody());
        verify(petService, times(1)).addPet(pet);
    }

    @Test
    public void testGetPetById() {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("bomboclat");

        when(petService.getPetById(1L)).thenReturn(Optional.of(pet));

        ResponseEntity<Pet> response = petController.getPetById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pet, response.getBody());
        verify(petService, times(1)).getPetById(1L);
    }
}