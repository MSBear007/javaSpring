package my.spring.app.test.restapi.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import my.spring.app.test.restapi.model.Actor;

public interface ActorRepository extends CrudRepository<Actor, Long>  {
    Optional<Actor> findByName(String name);
}
