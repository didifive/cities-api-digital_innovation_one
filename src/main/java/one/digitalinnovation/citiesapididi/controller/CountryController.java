package one.digitalinnovation.citiesapididi.controller;

import lombok.AllArgsConstructor;

import one.digitalinnovation.citiesapididi.entity.Country;
import one.digitalinnovation.citiesapididi.exception.CountryNotFoundException;
import one.digitalinnovation.citiesapididi.repository.CountryRepository;

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

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/countries")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CountryController {

    private static Logger log = LoggerFactory.getLogger(StateController.class);
    private final CountryRepository repository;

//    public CountryResource(final CountryRepository repository) {
//        this.repository = repository;
//    }

    @GetMapping
    public Page<Country> countries(final Pageable page) {
        log.info("countries, {}", page);
        return repository.findAll(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id) throws CountryNotFoundException {
        Optional<Country> countryOptional = repository.findById(id);
        if (countryOptional.isPresent()) return ResponseEntity.ok().body(countryOptional.get());
        throw new CountryNotFoundException(id);
    }
    
}
