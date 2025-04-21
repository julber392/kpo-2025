package hse.kpo.homework2.domain;

import hse.kpo.homework2.domain.events.AnimalMovedEvent;
import hse.kpo.homework2.domain.events.DomainEventPublisher;
import hse.kpo.homework2.domain.valueObjects.AnimalName;
import hse.kpo.homework2.domain.valueObjects.AnimalSpecies;
import hse.kpo.homework2.domain.valueObjects.FoodType;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class Animal {
    private final UUID id;
    private final AnimalSpecies species;
    private final AnimalName name;
    private final LocalDate birthDate;
    private final String gender;
    private final FoodType favoriteFood;
    private boolean isHealthy;
    private UUID enclosureId;

    public Animal(AnimalSpecies species, AnimalName name, LocalDate birthDate,
                  String gender, FoodType favoriteFood) {
        this.id = UUID.randomUUID();
        this.species = species;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.favoriteFood = favoriteFood;
        this.isHealthy = true;
    }

    public void feed() {
        System.out.println(name.toString() + "покормлен");
    }

    public void heal() {
        this.isHealthy = true;
    }

    public void moveToEnclosure(UUID enclosureId) {
        this.enclosureId = enclosureId;
        DomainEventPublisher.publish(new AnimalMovedEvent(this.id, enclosureId));
    }

}
