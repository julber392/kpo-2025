package hse.kpo.homework2.domain;

import hse.kpo.homework2.domain.Animal;
import hse.kpo.homework2.domain.valueObjects.AnimalName;
import hse.kpo.homework2.domain.valueObjects.AnimalSpecies;
import hse.kpo.homework2.domain.valueObjects.FoodType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

class AnimalTest {

    @Test
    void createAnimal_ShouldInitializeFields() {
        Animal animal = new Animal(
                new AnimalSpecies("Lion"),
                new AnimalName("Simba"),
                LocalDate.of(2020, 5, 15),
                "Male",
                new FoodType("Meat")
        );

        assertEquals("Lion", animal.getSpecies().value());
        assertEquals("Simba", animal.getName().value());
        assertTrue(animal.isHealthy());
    }
    @Test
    void heal_ShouldSetHealthyStatus() {
        Animal animal = new Animal(new AnimalSpecies("Lion"),
                new AnimalName("Simba"),
                LocalDate.of(2020, 5, 15),
                "Male",
                new FoodType("Meat"));
        animal.heal();
        assertTrue(animal.isHealthy());
    }
}
