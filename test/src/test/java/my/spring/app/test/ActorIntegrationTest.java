package my.spring.app.test;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import my.spring.app.test.restapi.model.Actor;
import my.spring.app.test.restapi.model.Country;
import my.spring.app.test.restapi.repositories.ActorRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ActorIntegrationTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    ActorRepository repository;

    @WithMockUser
    @Test
    public void testGet0ActorsAnd1Actor() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/actor/"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(0)));

        ArrayList<Actor> actors = new ArrayList<>();
        actors.add(new Actor(0, "Brad", new java.sql.Date(5675765), "Male", new Country("USA", "001"), "why.jpg"));
        when(repository.findAll()).thenReturn(actors);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/actor/"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
    }
}