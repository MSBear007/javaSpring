package my.spring.app.test.restapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import my.spring.app.test.restapi.model.Movie;
import my.spring.app.test.restapi.model.MoviesRatings;
import my.spring.app.test.restapi.model.User;

public interface MoviesRatingsRepository extends CrudRepository<MoviesRatings, Long> {

    @Query(value = """
        SELECT record FROM MoviesRatings record
        WHERE record.movie = ?1 AND record.user = ?2
    """)
    List<MoviesRatings> findByMovieAndUser(Movie movie, User user);

    @Query(value = """
        SELECT COUNT(record) FROM MoviesRatings record
        WHERE record.movie = ?1
    """)
    long countByMovie(Movie movie);
}
