package hse.kpo.homework2.domain;

import hse.kpo.homework2.domain.events.DomainEventPublisher;
import hse.kpo.homework2.domain.events.FeedingTimeEvent;
import hse.kpo.homework2.domain.valueObjects.FoodType;
import lombok.Getter;

import java.time.LocalTime;
import java.util.UUID;

@Getter
public class FeedingSchedule {
    private final UUID id;
    private final UUID animalId;
    private LocalTime feedingTime;
    private final FoodType foodType;
    private boolean isCompleted;

    public FeedingSchedule(UUID animalId, LocalTime feedingTime, FoodType foodType) {
        this.id = UUID.randomUUID();
        this.animalId = animalId;
        this.feedingTime = feedingTime;
        this.foodType = foodType;
        this.isCompleted = false;
    }

    // Domain methods
    public void reschedule(LocalTime newTime) {
        this.feedingTime = newTime;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
        DomainEventPublisher.publish(new FeedingTimeEvent(animalId, feedingTime, true));
    }
}
