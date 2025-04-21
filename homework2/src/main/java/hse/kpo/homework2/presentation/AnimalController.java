package hse.kpo.homework2.presentation;

import hse.kpo.homework2.application.AnimalRepository;
import hse.kpo.homework2.application.AnimalTransferService;
import hse.kpo.homework2.domain.Animal;
import hse.kpo.homework2.domain.valueObjects.AnimalName;
import hse.kpo.homework2.domain.valueObjects.AnimalSpecies;
import hse.kpo.homework2.domain.valueObjects.FoodType;
import hse.kpo.homework2.presentation.dto.AnimalDto;
import hse.kpo.homework2.presentation.dto.TransferRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {
    private final AnimalRepository animalRepository;
    private final AnimalTransferService transferService;

    public AnimalController(AnimalRepository animalRepository,
                            AnimalTransferService transferService) {
        this.animalRepository = animalRepository;
        this.transferService = transferService;
    }

    @GetMapping
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimal(@PathVariable UUID id) {
        return animalRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Animal> createAnimal(@RequestBody AnimalDto animalDto) {
        Animal animal = new Animal(
                new AnimalSpecies(animalDto.species()),
                new AnimalName(animalDto.name()),
                animalDto.birthDate(),
                animalDto.gender(),
                new FoodType(animalDto.favoriteFood())
        );
        animalRepository.save(animal);
        return ResponseEntity.status(201).body(animal);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable UUID id) {
        animalRepository.delete(id);
    }

    @PostMapping("/{id}/transfer")
    public void transferAnimal(@PathVariable UUID id, @RequestBody TransferRequest request) {
        transferService.transferAnimal(id, request.newEnclosureId());
    }
}
