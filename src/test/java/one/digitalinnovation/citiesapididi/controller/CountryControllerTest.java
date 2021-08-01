package one.digitalinnovation.citiesapididi.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CountryControllerTest {

    public static final String URL = "http://localhost:";
    private static final String COUNTRY_API_URL_PATH = "/api/v1/countries";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnCountries() {
        String response = restTemplate.getForObject(URL + port + COUNTRY_API_URL_PATH, String.class);

        assertThat(response).contains("Brasil");
        assertThat(response).contains("Brazil");
    }
}
