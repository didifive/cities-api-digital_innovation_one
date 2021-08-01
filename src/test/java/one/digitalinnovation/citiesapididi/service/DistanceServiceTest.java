package one.digitalinnovation.citiesapididi.service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.citiesapididi.entity.City;
import one.digitalinnovation.citiesapididi.entity.Country;
import one.digitalinnovation.citiesapididi.entity.State;
import one.digitalinnovation.citiesapididi.repository.CityRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.geo.Point;

import java.util.Arrays;
import static java.util.Arrays.asList;

@SpringBootTest
class DistanceServiceTest {

    @Autowired
    private DistanceService service;

    @MockBean
    private CityRepository cityRepository;

    private City saoCarlos;
    private City ibate;

    @BeforeEach
    public void setUp() {
        final Country country = new Country(1L,"Brazil","Brasil","BR",1058);
        final State state = new State(1L, "São Paulo", "SP", 35, country,Arrays.asList(11, 12, 13));
        ibate = new City(4929L, "Ibaté", state, 3519303, "(-21.95840072631836,-47.98820114135742)",
                new Point(-21.95840072631836, -47.98820114135742));
        saoCarlos = new City(5254L, "São Carlos", state, 3548906, "(-22.01740074157715,-47.88600158691406)",
                new Point(-22.01740074157715, -47.88600158691406));
    }

    @Test
    public void shouldCalculateInMetersUsingMath() {
        given(cityRepository.findAllById(anyList())).willReturn(Arrays.asList(ibate, saoCarlos));

        Double distance = service.distanceUsingMath(4929L, 5254L, EarthRadius.METERS);

        assertThat(distance).isEqualTo(12426.810463475855);
    }

    @Test
    public void shouldCalculateInKilometersUsingMath() {
        given(cityRepository.findAllById(anyList())).willReturn(Arrays.asList(ibate, saoCarlos));

        Double distance = service.distanceUsingMath(4929L, 5254L, EarthRadius.KILOMETERS);

        assertThat(distance).isCloseTo(12.426, offset(0.001d));
    }

    @Test
    public void shouldCalculateInMilesUsingMath() {
        given(cityRepository.findAllById(anyList())).willReturn(Arrays.asList(ibate, saoCarlos));

        Double distance = service.distanceUsingMath(4929L, 5254L, EarthRadius.MILES);

        assertThat(distance).isCloseTo(7.71, offset(0.01d));
    }

    @Test
    public void shouldCalculateInMetersUsingPoints() {
        given(cityRepository.findAllById(anyList())).willReturn(Arrays.asList(ibate, saoCarlos));

        Double distance = service.distanceUsingPoints(4929L, 5254L, EarthRadius.METERS);

        assertThat(distance).isEqualTo(12426.810463475855);
    }
}