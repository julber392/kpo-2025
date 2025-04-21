package hse.kpo.homework2.application;

import hse.kpo.homework2.domain.FeedingSchedule;
import hse.kpo.homework2.domain.valueObjects.FoodType;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.UUID;
@Service
public class FeedingOrganizationService {
    private final FeedingScheduleRepository scheduleRepository;
    private final AnimalRepository animalRepository;

    public FeedingOrganizationService(FeedingScheduleRepository scheduleRepository,
                                      AnimalRepository animalRepository) {
        this.scheduleRepository = scheduleRepository;
        this.animalRepository = animalRepository;
    }

    public void scheduleFeeding(UUID animalId, LocalTime time, FoodType foodType) {
        animalRepository.findById(animalId)
                .orElseThrow(() -> new IllegalArgumentException("Animal not found"));

        FeedingSchedule schedule = new FeedingSchedule(animalId, time, foodType);
        scheduleRepository.save(schedule);
    }

    public void completeFeeding(UUID scheduleId) {
        FeedingSchedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found"));

        schedule.markAsCompleted();
        scheduleRepository.save(schedule);
    }
}
