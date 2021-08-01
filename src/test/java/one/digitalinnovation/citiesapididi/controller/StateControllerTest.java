package one.digitalinnovation.citiesapididi.controller;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import one.digitalinnovation.citiesapididi.entity.Country;
import one.digitalinnovation.citiesapididi.entity.State;
import one.digitalinnovation.citiesapididi.repository.StateRepository;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(StateController.class)
class StateControllerTest {

    private static final String STATE_API_URL_PATH = "/api/v1/states";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StateRepository repository;

    @Test
    public void shouldReturnStates() throws Exception {
        final Country country = new Country(1L,"Brazil","Brasil","BR",1058);
        State state = new State(1L, "São Paulo", "SP", 35, country, asList(1, 2, 3));
        when(repository.findAll(PageRequest.of(0, 20, Sort.unsorted())))
                .thenReturn(new PageImpl<>(asList(state)));

        mockMvc.perform(get(STATE_API_URL_PATH).accept("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.content.[0].name", is("São Paulo")))
                .andExpect(jsonPath("$.content.[0].uf", is("SP")));
    }

}
