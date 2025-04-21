package hse.kpo.homework2.presentation;

import hse.kpo.homework2.application.EnclosureRepository;
import hse.kpo.homework2.domain.Enclosure;
import hse.kpo.homework2.domain.valueObjects.EnclosureType;
import hse.kpo.homework2.presentation.dto.EnclosureDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/enclosures")
public class EnclosureController {
    private final EnclosureRepository enclosureRepository;

    public EnclosureController(EnclosureRepository enclosureRepository) {
        this.enclosureRepository = enclosureRepository;
    }

    @GetMapping
    public List<Enclosure> getAllEnclosures() {
        return enclosureRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enclosure> getEnclosure(@PathVariable UUID id) {
        return enclosureRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Enclosure> createEnclosure(@RequestBody EnclosureDto enclosureDto) {
        Enclosure enclosure = new Enclosure(
                new EnclosureType(enclosureDto.type()),
                enclosureDto.maxCapacity()
        );
        enclosureRepository.save(enclosure);
        return ResponseEntity.status(201).body(enclosure); // Возвращаем статус 201 Created
    }


    @DeleteMapping("/{id}")
    public void deleteEnclosure(@PathVariable UUID id) {
        enclosureRepository.delete(id);
    }
}