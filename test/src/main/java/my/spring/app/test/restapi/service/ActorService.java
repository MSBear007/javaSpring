package my.spring.app.test.restapi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;

import my.spring.app.test.dto.ActorDto;
import my.spring.app.test.dto.GetActorDto;
import my.spring.app.test.exceptions.ResourceAlreadyExistsException;
import my.spring.app.test.exceptions.ResourceNotFoundException;
import my.spring.app.test.restapi.model.Actor;
import my.spring.app.test.restapi.repositories.ActorRepository;
import my.spring.app.test.restapi.repositories.CountryRepository;

@Service
@Transactional
public class ActorService {
    //private static final Logger log = LogManager.getLogger(ActorService.class);
    private final String pathToThumbnails = "C:\\Users\\bluff\\Desktop\\javaSpring\\test\\static\\img\\actors\\";
    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private CountryRepository countryRepository;


    public Actor addActor(ActorDto actorDto) throws ResourceNotFoundException, IOException, ResourceAlreadyExistsException {
        if (!actorRepository.findByName(actorDto.getName()).isPresent()) 
            throw new ResourceAlreadyExistsException("actor already exists in database");
        return actorRepository.save(newActor(actorDto));
    }


    public Iterable<Actor> getAllActors() {
        return actorRepository.findAll();
    }


    public Actor updateActor(ActorDto actorDto) throws ResourceNotFoundException, IOException {
        
        return actorRepository.save(newActor(actorDto));
    }

    private Actor newActor(ActorDto actorDto) throws ResourceNotFoundException, IOException {
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
        MultipartFile thumbnail = actorDto.getThumbnail();
        if (!thumbnail.isEmpty()) {
            byte[] bytes = thumbnail.getBytes();
            Path path = Paths.get(pathToThumbnails + thumbnail.getOriginalFilename());
            Files.write(path, bytes);
            actor.setThumbnailPath(thumbnail.getOriginalFilename());
        }
        return actor;
    }

    public GetActorDto getActorWithThumbnail(long id) throws ResourceNotFoundException, IOException {
        Optional<Actor> actor = actorRepository.findById(id);
        if (actor.isEmpty()) throw new ResourceNotFoundException();
        GetActorDto actorDto = new GetActorDto();
        actorDto.setName(actor.get().getName());
        actorDto.setCountry(actor.get().getCountry().getName());
        actorDto.setBirthDate(actor.get().getBirthDate().toString());
        actorDto.setSex(actor.get().getSex());
        byte[] content = Files.readAllBytes(Paths.get(pathToThumbnails + actor.get().getThumbnailPath()));
        actorDto.setThumbnail(content);
        return actorDto;
    }
}
