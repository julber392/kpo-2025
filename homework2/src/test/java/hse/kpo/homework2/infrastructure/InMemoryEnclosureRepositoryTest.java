package hse.kpo.homework2.infrastructure;

import hse.kpo.homework2.domain.Enclosure;
import hse.kpo.homework2.domain.valueObjects.EnclosureType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryEnclosureRepositoryTest {

    private InMemoryEnclosureRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryEnclosureRepository();
    }

    @Test
    void save_shouldStoreEnclosure() {
        Enclosure enclosure = new Enclosure(new EnclosureType("Predator"), 2);
        repository.save(enclosure);

        Optional<Enclosure> result = repository.findById(enclosure.getId());
        assertTrue(result.isPresent());
        assertEquals(enclosure, result.get());
    }

    @Test
    void findById_shouldReturnEmptyIfNotFound() {
        UUID randomId = UUID.randomUUID();
        Optional<Enclosure> result = repository.findById(randomId);
        assertTrue(result.isEmpty());
    }

    @Test
    void findAll_shouldReturnAllSavedEnclosures() {
        Enclosure e1 = new Enclosure(new EnclosureType("Predator"), 2);
        Enclosure e2 = new Enclosure(new EnclosureType("Herbivore"), 3);

        repository.save(e1);
        repository.save(e2);

        List<Enclosure> all = repository.findAll();
        assertEquals(2, all.size());
        assertTrue(all.contains(e1));
        assertTrue(all.contains(e2));
    }

    @Test
    void delete_shouldRemoveEnclosureById() {
        Enclosure enclosure = new Enclosure(new EnclosureType("Omnivore"), 5);
        repository.save(enclosure);

        repository.delete(enclosure.getId());

        Optional<Enclosure> result = repository.findById(enclosure.getId());
        assertTrue(result.isEmpty());
    }
}
