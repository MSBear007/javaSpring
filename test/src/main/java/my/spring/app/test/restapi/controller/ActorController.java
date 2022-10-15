package my.spring.app.test.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import my.spring.app.test.dto.ActorDto;
import my.spring.app.test.exceptions.ResourceNotFoundException;
import my.spring.app.test.restapi.model.Actor;
import my.spring.app.test.restapi.service.ActorService;

@RestController
@RequestMapping(path = "/${v1Api}/actor")
public class ActorController {
    @Autowired
    private ActorService service;

    @PostMapping(path = "/add")
    public Actor postActor(@ModelAttribute("actor") ActorDto actorDto) throws ResourceNotFoundException {
        return service.addActor(actorDto);
    }

    @PutMapping(path="/update")
    public Actor updateActor(@ModelAttribute("actor") ActorDto actorDto) throws ResourceNotFoundException {
        return service.updateActor(actorDto);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Actor> getAllActors() {
        return service.getAllActors();
    }
}
