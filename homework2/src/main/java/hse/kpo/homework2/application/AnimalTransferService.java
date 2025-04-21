package hse.kpo.homework2.application;
import hse.kpo.homework2.domain.Animal;
import hse.kpo.homework2.domain.Enclosure;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class AnimalTransferService {
    private final AnimalRepository animalRepository;
    private final EnclosureRepository enclosureRepository;

    public AnimalTransferService(AnimalRepository animalRepository,
                                 EnclosureRepository enclosureRepository) {
        this.animalRepository = animalRepository;
        this.enclosureRepository = enclosureRepository;
    }

    public void transferAnimal(UUID animalId, UUID newEnclosureId) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new IllegalArgumentException("Animal not found"));

        Enclosure newEnclosure = enclosureRepository.findById(newEnclosureId)
                .orElseThrow(() -> new IllegalArgumentException("Enclosure not found"));


        if (!newEnclosure.getType().value().equals(getEnclosureTypeForAnimal(animal.getSpecies().value()))) {
            throw new IllegalStateException("Incompatible enclosure type for this animal");
        }

        if (animal.getEnclosureId() != null) {
            Enclosure oldEnclosure = enclosureRepository.findById(animal.getEnclosureId())
                    .orElseThrow(() -> new IllegalStateException("Current enclosure not found"));
            oldEnclosure.removeAnimal(animal);
            enclosureRepository.save(oldEnclosure);
        }

        newEnclosure.addAnimal(animal);
        enclosureRepository.save(newEnclosure);

        animal.moveToEnclosure(newEnclosureId);
        animalRepository.save(animal);
    }

    private String getEnclosureTypeForAnimal(String species) {
        if (species.equals("Lion") || species.equals("Tiger")) {
            return "Predator";
        }
        return "Herbivore";
    }
}
