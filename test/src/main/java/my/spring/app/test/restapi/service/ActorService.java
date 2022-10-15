package my.spring.app.test.restapi.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.spring.app.test.dto.ActorDto;
import my.spring.app.test.exceptions.ResourceNotFoundException;
import my.spring.app.test.restapi.model.Actor;
import my.spring.app.test.restapi.repositories.ActorRepository;
import my.spring.app.test.restapi.repositories.CountryRepository;

@Service
@Transactional
public class ActorService {
    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private CountryRepository countryRepository;


    public Actor addActor(ActorDto actorDto) throws ResourceNotFoundException {
        Actor actor = new Actor();
        actor.setName(actorDto.getName());
        actor.setSex(actorDto.getSex());
        actor.setBirthDate(java.sql.Date.valueOf(actorDto.getBirthDate()));
        if (actorDto.getCountry() == null) {
            actor.setCountry(null);
        }
        else if (actorDto.getCountry().isBlank()) {
            actor.setCountry(null);
        }
        else if (!countryRepository.findByName(actorDto.getCountry()).isPresent()) {
            throw new ResourceNotFoundException("No such country exists");
        }
        else {
            actor.setCountry(countryRepository.findByName(actorDto.getCountry()).get());
        }
        return actorRepository.save(actor);
    }


    public Iterable<Actor> getAllActors() {
        return actorRepository.findAll();
    }


    public Actor updateActor(ActorDto actorDto) throws ResourceNotFoundException {
        Actor actor = new Actor();
        actor.setName(actorDto.getName());
        actor.setSex(actorDto.getSex());
        actor.setBirthDate(java.sql.Date.valueOf(actorDto.getBirthDate()));
        if (actorDto.getCountry() == null) {
            actor.setCountry(null);
        }
        else if (actorDto.getCountry().isBlank()) {
            actor.setCountry(null);
        }
        else if (!countryRepository.findByName(actorDto.getCountry()).isPresent()) {
            throw new ResourceNotFoundException("No such country exists");
        }
        else {
            actor.setCountry(countryRepository.findByName(actorDto.getCountry()).get());
        }
        return actorRepository.save(actor);
    }
}
