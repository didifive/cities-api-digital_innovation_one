package one.digitalinnovation.citiesapididi.controller;

import lombok.AllArgsConstructor;
import one.digitalinnovation.citiesapididi.entity.City;
import one.digitalinnovation.citiesapididi.entity.State;
import one.digitalinnovation.citiesapididi.exception.CityNotFoundException;
import one.digitalinnovation.citiesapididi.exception.StateNotFoundException;
import one.digitalinnovation.citiesapididi.repository.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {

    private static Logger log = LoggerFactory.getLogger(CityController.class);
    private final CityRepository repository;

    public CityController(final CityRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Page<City> cities(final Pageable page) {
        log.info("cities, {}", page);
        return repository.findAll(page);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<City> findById(@PathVariable Long id) throws CityNotFoundException {
        Optional<City> cityOptional = repository.findById(id);
        if (cityOptional.isPresent()) return ResponseEntity.ok().body(cityOptional.get());
        throw new CityNotFoundException(id);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Stream<City>> findByNameStartWith(@PathVariable String name) throws CityNotFoundException {
        List<City> cityPage = repository.findByNameStartWith(name);
        if (cityPage.isEmpty()) throw new CityNotFoundException(name);
        return ResponseEntity.ok().body(cityPage.stream());
    }
}
