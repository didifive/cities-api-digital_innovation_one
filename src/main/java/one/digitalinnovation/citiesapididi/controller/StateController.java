package one.digitalinnovation.citiesapididi.controller;

import lombok.AllArgsConstructor;
import one.digitalinnovation.citiesapididi.entity.State;
import one.digitalinnovation.citiesapididi.exception.StateNotFoundException;
import one.digitalinnovation.citiesapididi.repository.StateRepository;
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
@RequestMapping("/api/v1/states")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StateController {

    private static Logger log = LoggerFactory.getLogger(StateController.class);
    private final StateRepository repository;

//    public StateController(final StateRepository repository) {
//        this.repository = repository;
//    }

    @GetMapping
    public Page<State> states(final Pageable page) {
        log.info("states, {}", page);
        return repository.findAll(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<State> findById(@PathVariable Long id) throws StateNotFoundException {
        Optional<State> stateOptional = repository.findById(id);
        if (stateOptional.isPresent()) return ResponseEntity.ok().body(stateOptional.get());
        throw new StateNotFoundException(id);
    }
}
