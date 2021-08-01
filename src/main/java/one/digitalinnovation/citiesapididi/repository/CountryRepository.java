package one.digitalinnovation.citiesapididi.repository;

import one.digitalinnovation.citiesapididi.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}