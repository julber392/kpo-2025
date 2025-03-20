package hse.kpo.bighomework1.services;

import hse.kpo.bighomework1.entity.animals.abstracts.Animal;
import hse.kpo.bighomework1.entity.animals.abstracts.Herbo;
import hse.kpo.bighomework1.services.interfaces.IAnimalProvider;
import hse.kpo.bighomework1.entity.interfaces.IInventory;
import hse.kpo.bighomework1.services.interfaces.IThingProvider;
import hse.kpo.bighomework1.services.interfaces.IVetClinic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ZooService {
    private final IAnimalProvider animalProvider;
    private final IThingProvider thingProvider;
    private final IVetClinic vetClinic;
    public void checkAnimal(Animal animal){
        if (vetClinic.checkHealth(animal)){
            animalProvider.addAnimal(animal);
        } else{
            System.out.println("В зоопарк не принято животное по состоянию здоровья: "+animal.getName());
        }
    }
    public void takeItem(IInventory thing){
        thingProvider.addThing(thing);
    }
    public void showAnimalReport() {
        var animals=animalProvider.getAnimals();
        int totalFood = 0;
        for (Animal animal : animals) {
            totalFood += animal.getFood();
            System.out.println(animal);
        }
        System.out.println("Общий расход еды: " + totalFood + " кг");
    }
    public void showFriendlyAnimals() {
        var animals=animalProvider.getAnimals();
        for (Animal animal : animals) {
            if (animal instanceof Herbo && ((Herbo) animal).isFriendly()) {
                System.out.println(animal.getName() + " может быть в контактном зоопарке.");
            }
        }
    }
    public void showInventory() {
        var inventory=thingProvider.getInventory();
        for (IInventory inv : inventory) {
            System.out.println(inv);
        }
    }
}