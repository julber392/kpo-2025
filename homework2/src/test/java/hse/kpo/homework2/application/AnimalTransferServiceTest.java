package hse.kpo.homework2.application;

import hse.kpo.homework2.application.AnimalRepository;
import hse.kpo.homework2.application.AnimalTransferService;
import hse.kpo.homework2.application.EnclosureRepository;
import hse.kpo.homework2.domain.Animal;
import hse.kpo.homework2.domain.Enclosure;
import hse.kpo.homework2.domain.valueObjects.AnimalName;
import hse.kpo.homework2.domain.valueObjects.AnimalSpecies;
import hse.kpo.homework2.domain.valueObjects.EnclosureType;
import hse.kpo.homework2.domain.valueObjects.FoodType;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AnimalTransferServiceTest {

    @Test
    void transferAnimal_ShouldChangeEnclosure() {
        // Подготовка
        AnimalRepository animalRepo = mock(AnimalRepository.class);
        EnclosureRepository enclosureRepo = mock(EnclosureRepository.class);

        Animal animal = new Animal( new AnimalSpecies("Lion"),
                new AnimalName("Simba"),
                LocalDate.of(2020, 5, 15),
                "Male",
                new FoodType("Meat"));
        Enclosure oldEnclosure = new Enclosure(new EnclosureType("Predator"),  1);
        Enclosure newEnclosure = new Enclosure(new EnclosureType("Predator"),  1);

        when(animalRepo.findById(any())).thenReturn(Optional.of(animal));
        when(enclosureRepo.findById(any())).thenReturn(Optional.of(newEnclosure));

        // Вызов
        AnimalTransferService service = new AnimalTransferService(animalRepo, enclosureRepo);
        service.transferAnimal(animal.getId(), newEnclosure.getId());

        // Проверка
        verify(animalRepo).save(animal);
        assertEquals(newEnclosure.getId(), animal.getEnclosureId());
    }
}
