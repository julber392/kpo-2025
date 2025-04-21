package hse.kpo.homework2.domain;

import hse.kpo.homework2.domain.Animal;
import hse.kpo.homework2.domain.Enclosure;
import hse.kpo.homework2.domain.valueObjects.AnimalName;
import hse.kpo.homework2.domain.valueObjects.AnimalSpecies;
import hse.kpo.homework2.domain.valueObjects.EnclosureType;
import hse.kpo.homework2.domain.valueObjects.FoodType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class EnclosureTest {

    @Test
    void addAnimal_WhenNotFull_ShouldAddAnimal() {
        Enclosure enclosure = new Enclosure(new EnclosureType("Predator"),  2);
        Animal animal = new Animal(new AnimalSpecies("Lion"),
                new AnimalName("Simba"),
                LocalDate.of(2020, 5, 15),
                "Male",
                new FoodType("Meat"));

        enclosure.addAnimal(animal);
        assertEquals(1, enclosure.getSize());
    }

    @Test
    void addAnimal_WhenFull_ShouldThrowException() {
        Enclosure enclosure = new Enclosure(new EnclosureType("Predator"),  1);
        enclosure.addAnimal(new Animal(new AnimalSpecies("Lion"),
                new AnimalName("Simba"),
                LocalDate.of(2020, 5, 15),
                "Male",
                new FoodType("Meat")));

        assertThrows(IllegalStateException.class,
                () -> enclosure.addAnimal(new Animal(new AnimalSpecies("Lion"),
                        new AnimalName("Simba"),
                        LocalDate.of(2020, 5, 15),
                        "Male",
                        new FoodType("Meat"))));
    }
}
