package hse.kpo.homework2.infrastructure;

import hse.kpo.homework2.domain.FeedingSchedule;
import hse.kpo.homework2.domain.valueObjects.FoodType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryFeedingScheduleRepositoryTest {

    private InMemoryFeedingScheduleRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryFeedingScheduleRepository();
    }

    @Test
    void save_shouldStoreSchedule() {
        FeedingSchedule schedule = new FeedingSchedule(UUID.randomUUID(), LocalTime.of(10, 0), new FoodType("Meat"));
        repository.save(schedule);

        Optional<FeedingSchedule> result = repository.findById(schedule.getId());
        assertTrue(result.isPresent());
        assertEquals(schedule, result.get());
    }

    @Test
    void findById_shouldReturnEmptyForUnknownId() {
        Optional<FeedingSchedule> result = repository.findById(UUID.randomUUID());
        assertTrue(result.isEmpty());
    }

    @Test
    void findAll_shouldReturnAllSavedSchedules() {
        FeedingSchedule s1 = new FeedingSchedule(UUID.randomUUID(), LocalTime.of(9, 0), new FoodType("Fish"));
        FeedingSchedule s2 = new FeedingSchedule(UUID.randomUUID(), LocalTime.of(12, 30), new FoodType("Fruit"));

        repository.save(s1);
        repository.save(s2);

        List<FeedingSchedule> all = repository.findAll();
        assertEquals(2, all.size());
        assertTrue(all.contains(s1));
        assertTrue(all.contains(s2));
    }

    @Test
    void delete_shouldRemoveSchedule() {
        FeedingSchedule schedule = new FeedingSchedule(UUID.randomUUID(), LocalTime.of(14, 0), new FoodType("Seeds"));
        repository.save(schedule);

        repository.delete(schedule.getId());

        Optional<FeedingSchedule> result = repository.findById(schedule.getId());
        assertTrue(result.isEmpty());
    }
}
