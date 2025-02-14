package hse.kpo.homework1.services;

import hse.kpo.homework1.entity.animals.abstracts.Animal;
import hse.kpo.homework1.services.interfaces.IVetClinic;
import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class VetClinic implements IVetClinic {
    public boolean checkHealth(Animal animal) {
        return new Random().nextBoolean();
    }
}
