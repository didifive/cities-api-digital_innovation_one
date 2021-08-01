package one.digitalinnovation.citiesapididi.controller;

import one.digitalinnovation.citiesapididi.entity.City;
import one.digitalinnovation.citiesapididi.service.DistanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/nearby")
public class NearbyController {

    private static Logger log = LoggerFactory.getLogger(NearbyController.class);
    private final DistanceService service;

    public NearbyController(DistanceService service) {
        this.service = service;
    }

    @GetMapping
    public List<City> nearby(@RequestParam(name = "city_id") final Long cityId,
                             @RequestParam(name = "radius") final Double radius) {
        log.info("nearby, city_id={}, radius={}", cityId, radius);
        return service.nearby(cityId, radius);
    }
}
