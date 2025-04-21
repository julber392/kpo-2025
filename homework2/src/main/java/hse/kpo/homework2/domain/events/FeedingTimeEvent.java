package hse.kpo.homework2.domain.events;

import java.time.LocalTime;
import java.util.UUID;

public record FeedingTimeEvent(UUID animalId, LocalTime time, boolean isCompleted) implements DomainEvent {}
