package one.digitalinnovation.citiesapididi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CityNotFoundException extends Exception {
    public CityNotFoundException(Long id) {
        super(String.format("City with id %s not found in the system.", id));
    }

    public CityNotFoundException(String name) {
        super(String.format("City with name /'%s/' not found in the system.", name));
    }
}