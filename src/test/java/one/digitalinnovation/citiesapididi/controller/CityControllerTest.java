package one.digitalinnovation.citiesapididi.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CityControllerTest {

    public static final String URL = "http://localhost:";
    private static final String CITY_API_URL_PATH = "/api/v1/cities";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnAPageOfCity() {
        String response =
                restTemplate.getForObject(URL + port + CITY_API_URL_PATH, String.class);

        assertThat(response).contains("Ibaté");
        assertThat(response).contains("São Carlos");
    }
}