package my.spring.app.test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import my.spring.app.test.dto.ActorDto;
import my.spring.app.test.restapi.controller.ActorController;
import my.spring.app.test.restapi.model.Actor;
import my.spring.app.test.restapi.model.Country;
import my.spring.app.test.restapi.repositories.ActorRepository;
import my.spring.app.test.restapi.service.ActorService;

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
        new java.sql.Date(85765876), "Male", new Country("Finland", "01"), "thumb.jpg");
    Actor RECORD_2 = new Actor(2, "Michael", 
        new java.sql.Date(85765876), "Male", new Country("Finland", "01"), "thumb.jpg");
    Actor RECORD_3 = new Actor(3, "Lula", 
        new java.sql.Date(85765876), "Male", new Country("Finland", "01"), "thumb.jpg");
    Actor RECORD_4 = new Actor(4, "Jane", 
        new java.sql.Date(85765876), "Male", new Country("Finland", "01"), "thumb.jpg");

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

    @WithMockUser
    @Test
    public void postTest() throws Exception {
        
        Mockito.when(service.addActor(Mockito.any(ActorDto.class))).thenReturn(RECORD_1);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/actor/")
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .param("name", "George")
            .param("birthDate", "1999-11-11")
            .param("sex", "Male")
            .param("country", "Finland")
            .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("George")));
        
    }
}
