package hse.kpo.homework2.application;

import hse.kpo.homework2.domain.Animal;
import hse.kpo.homework2.domain.Enclosure;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ZooStatisticsService {
    private final AnimalRepository animalRepository;
    private final EnclosureRepository enclosureRepository;

    public ZooStatisticsService(AnimalRepository animalRepository,
                                EnclosureRepository enclosureRepository) {
        this.animalRepository = animalRepository;
        this.enclosureRepository = enclosureRepository;
    }

    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();

        List<Animal> animals = animalRepository.findAll();
        List<Enclosure> enclosures = enclosureRepository.findAll();

        stats.put("totalAnimals", animals.size());
        stats.put("totalEnclosures", enclosures.size());
        stats.put("freeEnclosures",
                enclosures.stream().filter(e -> e.getSize() == 0).count());
        return stats;
    }
}