package hse.kpo.bighomework1.services.interfaces;

import hse.kpo.bighomework1.entity.animals.abstracts.Animal;

import java.util.List;

public interface IAnimalProvider {
    List<Animal> getAnimals();
    void addAnimal(Animal animal);
}
