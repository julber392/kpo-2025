package hse.kpo.homework2.presentation.dto;

import java.time.LocalTime;
import java.util.UUID;

public record ScheduleDto(UUID animalId, LocalTime time, String foodType) {}
