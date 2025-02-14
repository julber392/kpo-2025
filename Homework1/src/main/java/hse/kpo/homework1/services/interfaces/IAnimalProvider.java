package hse.kpo.homework1.services.interfaces;

import hse.kpo.homework1.entity.animals.abstracts.Animal;

import java.util.List;

public interface IAnimalProvider {
    List<Animal> getAnimals();
    void addAnimal(Animal animal);
}
