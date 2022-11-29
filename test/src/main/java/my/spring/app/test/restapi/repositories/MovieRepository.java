package my.spring.app.test.restapi.repositories;

import org.springframework.data.repository.CrudRepository;

import my.spring.app.test.restapi.model.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    
}
