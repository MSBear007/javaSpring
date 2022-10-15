package my.spring.app.test.restapi.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import my.spring.app.test.restapi.model.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
    Optional<UserRole> findByName(String name);
}
