package hse.kpo.homework2.infrastructure;

import hse.kpo.homework2.application.AnimalRepository;
import hse.kpo.homework2.domain.Animal;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class InMemoryAnimalRepository implements AnimalRepository {
    private final Map<UUID, Animal> animals = new HashMap<>();

    @Override
    public void save(Animal animal) {
        animals.put(animal.getId(), animal);
    }

    @Override
    public Optional<Animal> findById(UUID id) {
        return Optional.ofNullable(animals.get(id));
    }

    @Override
    public void delete(UUID id) {
        animals.remove(id);
    }

    @Override
    public List<Animal> findAll() {
        return new ArrayList<>(animals.values());
    }
}
