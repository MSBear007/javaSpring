package my.spring.app.test.restapi.controller;

import java.io.IOException;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import my.spring.app.test.dto.ActorDto;
import my.spring.app.test.dto.GetActorDto;
import my.spring.app.test.exceptions.ResourceAlreadyExistsException;
import my.spring.app.test.exceptions.ResourceNotFoundException;
import my.spring.app.test.restapi.model.Actor;
import my.spring.app.test.restapi.service.ActorService;

@RestController
@RequestMapping(path = "/${v1Api}/actor")
public class ActorController {
//private static final Logger log = LogManager.getLogger(ActorController.class);

    @Autowired
    private ActorService service;

    @PostMapping(path = "/add")
    public Actor postActor(@ModelAttribute("actor") ActorDto actorDto) throws ResourceNotFoundException, IOException, ResourceAlreadyExistsException {
        return service.addActor(actorDto);
    }

    @PutMapping(path="/update")
    public Actor updateActor(@ModelAttribute("actor") ActorDto actorDto) throws ResourceNotFoundException, IOException {
        return service.updateActor(actorDto);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Actor> getAllActors() {
        return service.getAllActors();
    }

    @GetMapping(path="/id/{id}")
    public @ResponseBody GetActorDto getActorByName(@PathVariable long id) throws ResourceNotFoundException, IOException {

        GetActorDto dto = service.getActorWithThumbnail(id);
        return dto;
    }
}
