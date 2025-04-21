package hse.kpo.homework2.application;

import hse.kpo.homework2.domain.Enclosure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EnclosureRepository {
    void save(Enclosure enclosure);
    Optional<Enclosure> findById(UUID id);
    void delete(UUID id);
    List<Enclosure> findAll();
}