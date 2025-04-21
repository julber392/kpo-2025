package hse.kpo.homework2.application;

import hse.kpo.homework2.domain.FeedingSchedule;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FeedingScheduleRepository {
    void save(FeedingSchedule schedule);
    Optional<FeedingSchedule> findById(UUID id);
    void delete(UUID id);
    List<FeedingSchedule> findAll();
}
