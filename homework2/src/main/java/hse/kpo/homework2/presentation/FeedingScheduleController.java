package hse.kpo.homework2.presentation;

import hse.kpo.homework2.application.FeedingOrganizationService;
import hse.kpo.homework2.application.FeedingScheduleRepository;
import hse.kpo.homework2.domain.FeedingSchedule;
import hse.kpo.homework2.domain.valueObjects.FoodType;
import hse.kpo.homework2.presentation.dto.ScheduleDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/feeding-schedules")
public class FeedingScheduleController {
    private final FeedingScheduleRepository scheduleRepository;
    private final FeedingOrganizationService feedingService;

    public FeedingScheduleController(FeedingScheduleRepository scheduleRepository,
                                     FeedingOrganizationService feedingService) {
        this.scheduleRepository = scheduleRepository;
        this.feedingService = feedingService;
    }

    @GetMapping
    public List<FeedingSchedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @PostMapping
    public FeedingSchedule createSchedule(@RequestBody ScheduleDto scheduleDto) {
        feedingService.scheduleFeeding(
                scheduleDto.animalId(),
                scheduleDto.time(),
                new FoodType(scheduleDto.foodType())
        );
        return scheduleRepository.findAll().stream()
                .filter(s -> s.getAnimalId().equals(scheduleDto.animalId()))
                .findFirst()
                .orElseThrow();
    }

    @PostMapping("/{id}/complete")
    public void completeFeeding(@PathVariable UUID id) {
        feedingService.completeFeeding(id);
    }
}

