package org.example.petstore.service;

import org.example.petstore.model.Pet;
import org.example.petstore.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet addPet(Pet pet) {
        return petRepository.save(pet);
    }

    public Optional<Pet> getPetById(Long id) {
        return petRepository.findById(id);
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public List<Pet> findByCategory(String category) {
        return petRepository.findAll().stream()
                .filter(pet -> pet.getCategory() != null && category.equalsIgnoreCase(pet.getCategory().getName()))
                .collect(Collectors.toList());
    }
}