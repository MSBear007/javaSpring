package my.spring.app.test.restapi.repositories;

import org.springframework.data.repository.CrudRepository;

import my.spring.app.test.restapi.model.Actor;

public interface ActorRepository extends CrudRepository<Actor, Long>  {
    
}
