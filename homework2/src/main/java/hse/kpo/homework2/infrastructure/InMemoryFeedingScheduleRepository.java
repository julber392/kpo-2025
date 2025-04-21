package hse.kpo.homework2.infrastructure;

import hse.kpo.homework2.application.FeedingScheduleRepository;
import hse.kpo.homework2.domain.FeedingSchedule;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class InMemoryFeedingScheduleRepository implements FeedingScheduleRepository {
    private final Map<UUID, FeedingSchedule> schedules = new HashMap<>();

    @Override
    public void save(FeedingSchedule schedule) {
        schedules.put(schedule.getId(), schedule);
    }

    @Override
    public Optional<FeedingSchedule> findById(UUID id) {
        return Optional.ofNullable(schedules.get(id));
    }

    @Override
    public void delete(UUID id) {
        schedules.remove(id);
    }

    @Override
    public List<FeedingSchedule> findAll() {
        return new ArrayList<>(schedules.values());
    }
}
