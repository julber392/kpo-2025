package hse.kpo.homework2.domain;

import hse.kpo.homework2.domain.valueObjects.EnclosureType;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
public class Enclosure {
    private final UUID id;
    private final EnclosureType type;
    private int size = 0;
    private final int maxCapacity;
    private final Set<UUID> animals = new HashSet<>();

    public Enclosure(EnclosureType type, int maxCapacity) {
        this.id = UUID.randomUUID();
        this.type = type;
        this.maxCapacity = maxCapacity;
    }

    public void addAnimal(Animal animal) {
        if (animals.size() >= maxCapacity) {
            throw new IllegalStateException("Enclosure is at full capacity");
        }
        animals.add(animal.getId());
        size = animals.size();
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal.getId());
        size = animals.size();
    }

}
