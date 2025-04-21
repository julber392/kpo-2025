package hse.kpo.homework2.domain.events;

import java.util.UUID;

public record AnimalMovedEvent(UUID animalId, UUID newEnclosureId) implements DomainEvent {}
