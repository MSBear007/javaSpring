package my.spring.app.test.restapi.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import my.spring.app.test.restapi.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String name);

    Iterable<User> findByEmail(String email);

    boolean existsByUsername(String name);
}
