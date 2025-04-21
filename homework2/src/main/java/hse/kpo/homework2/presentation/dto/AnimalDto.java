package hse.kpo.homework2.presentation.dto;

import java.time.LocalDate;

public record AnimalDto(String species, String name, LocalDate birthDate,
                        String gender, String favoriteFood) {}

