package hse.kpo.homework2.application;

import hse.kpo.homework2.domain.Animal;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnimalRepository {
    void save(Animal animal);
    Optional<Animal> findById(UUID id);
    void delete(UUID id);
    List<Animal> findAll();
}
