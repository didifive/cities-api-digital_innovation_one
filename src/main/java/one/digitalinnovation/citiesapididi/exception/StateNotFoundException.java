package one.digitalinnovation.citiesapididi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StateNotFoundException extends Exception {
    public StateNotFoundException(Long id) {
        super(String.format("State with id %s not found in the system.", id));
    }
}