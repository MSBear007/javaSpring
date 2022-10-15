package my.spring.app.test.restapi.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import my.spring.app.test.restapi.model.Country;

public interface CountryRepository extends CrudRepository<Country, String> {

    Optional<Country> findByName(String name);
}
