package my.spring.app.test.restapi.controller;

import java.io.IOException;

import javax.validation.Valid;
import javax.validation.ValidationException;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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

    /**
     * POST method
     * @param actorDto - Actor Data Transfer Object
     * @see {@link my.spring.app.test.dto.ActorDto}
     * @param result - validation results
     */
    @PostMapping(path = "/")
    public Actor postActor(@Valid @ModelAttribute("actor") ActorDto actorDto, BindingResult result) throws ResourceNotFoundException, IOException, ResourceAlreadyExistsException {
        if (result.hasErrors()) throw new ValidationException(result.getAllErrors().toString());
        return service.addActor(actorDto);
    }

    /**
     * PUT method
     * @param actorDto Actor Data Transfer Object
     * @see {@link my.spring.app.test.dto.ActorDto}
     * @param result - validation results
     */
    @PutMapping(path="/")
    public Actor updateActor(@Valid @ModelAttribute("actor") ActorDto actorDto, BindingResult result) throws ResourceNotFoundException, IOException {
        if (result.hasErrors()) throw new ValidationException(result.getAllErrors().toString());
        return service.updateActor(actorDto);
    }

    /**
     * GET method (all actors)
     */
    @GetMapping(path="/")
    public @ResponseBody Iterable<Actor> getAllActors() {
        return service.getAllActors();
    }

    /**
     * GET method (by id)
     * @param id - id of actor
     * @return GETActor DTO
     * @see {@link my.spring.app.test.dto.GetActorDto}
     */
    @GetMapping(path="/{id}")
    public @ResponseBody GetActorDto getActorByName(@PathVariable long id) throws ResourceNotFoundException, IOException {

        GetActorDto dto = service.getActorWithThumbnail(id);
        return dto;
    }
}
