package hse.kpo.homework2.infrastructure;
import hse.kpo.homework2.domain.Animal;
import hse.kpo.homework2.domain.valueObjects.AnimalName;
import hse.kpo.homework2.domain.valueObjects.AnimalSpecies;
import hse.kpo.homework2.domain.valueObjects.FoodType;
import hse.kpo.homework2.infrastructure.InMemoryAnimalRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryAnimalRepositoryTest {

    @Test
    void save_ShouldStoreAnimal() {
        InMemoryAnimalRepository repo = new InMemoryAnimalRepository();
        Animal animal = new Animal( new AnimalSpecies("Lion"),
                new AnimalName("Simba"),
                LocalDate.of(2020, 5, 15),
                "Male",
                new FoodType("Meat"));

        repo.save(animal);
        Optional<Animal> found = repo.findById(animal.getId());

        assertTrue(found.isPresent());
        assertEquals(animal.getId(), found.get().getId());
    }
}
