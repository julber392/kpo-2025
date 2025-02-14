package hse.kpo.homework1.services;

import hse.kpo.homework1.entity.animals.abstracts.Animal;
import hse.kpo.homework1.services.interfaces.IAnimalProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnimalStorage implements IAnimalProvider {
    private final List<Animal> animals = new ArrayList<>();

    @Override
    public List<Animal> getAnimals() {
        return animals;
    }

    @Override
    public void addAnimal(Animal animal) {

        animals.add(animal);
        System.out.println("В зоопарк принято новое животное: " + animal.getName() + " id=" + animals.size());
    }
}
