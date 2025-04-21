package hse.kpo.homework2.infrastructure;

import hse.kpo.homework2.application.EnclosureRepository;
import hse.kpo.homework2.domain.Enclosure;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class InMemoryEnclosureRepository implements EnclosureRepository {
    private final Map<UUID, Enclosure> enclosures = new HashMap<>();

    @Override
    public void save(Enclosure enclosure) {
        enclosures.put(enclosure.getId(), enclosure);
    }

    @Override
    public Optional<Enclosure> findById(UUID id) {
        return Optional.ofNullable(enclosures.get(id));
    }

    @Override
    public void delete(UUID id) {
        enclosures.remove(id);
    }

    @Override
    public List<Enclosure> findAll() {
        return new ArrayList<>(enclosures.values());
    }
}
