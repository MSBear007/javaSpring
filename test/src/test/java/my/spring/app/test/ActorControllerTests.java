package my.spring.app.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import my.spring.app.test.restapi.controller.ActorController;
import my.spring.app.test.restapi.model.Actor;
import my.spring.app.test.restapi.model.Country;
import my.spring.app.test.restapi.repositories.ActorRepository;
import my.spring.app.test.restapi.service.ActorService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(ActorController.class)
public class ActorControllerTests {
    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    ActorRepository repository;

    @MockBean
    ActorService service;

    Actor RECORD_1 = new Actor(1, "George", 
        new java.sql.Date(85765876), "Male", new Country("Finalnd", "01"), "thumb.jpg");
    Actor RECORD_2 = new Actor(2, "Michael", 
        new java.sql.Date(85765876), "Male", new Country("Finalnd", "01"), "thumb.jpg");
    Actor RECORD_3 = new Actor(3, "Lula", 
        new java.sql.Date(85765876), "Male", new Country("Finalnd", "01"), "thumb.jpg");
    Actor RECORD_4 = new Actor(4, "Jane", 
        new java.sql.Date(85765876), "Male", new Country("Finalnd", "01"), "thumb.jpg");

    @WithMockUser
    @Test
    public void getTest() throws Exception {
        ArrayList<Actor> returned_from_service = new ArrayList<Actor>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3, RECORD_4));

        Mockito.when(service.getAllActors()).thenReturn(returned_from_service);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/actor/")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(4)))
            .andExpect(jsonPath("$[0].name", is("George")));
    }
}
