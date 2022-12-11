package my.spring.app.test.restapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import my.spring.app.test.dto.RatingDto;
import my.spring.app.test.events.UpdateRatingEvent;
import my.spring.app.test.exceptions.ResourceNotFoundException;
import my.spring.app.test.restapi.model.Movie;
import my.spring.app.test.restapi.model.MoviesRatings;
import my.spring.app.test.restapi.model.User;
import my.spring.app.test.restapi.repositories.MovieRepository;
import my.spring.app.test.restapi.repositories.MoviesRatingsRepository;
import my.spring.app.test.restapi.repositories.UserRepository;

@Service
@Transactional
public class RatingsService {
    @Autowired
    private MoviesRatingsRepository ratingsRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private MovieRepository moviesRepo;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public boolean addRating(RatingDto rating) throws ResourceNotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails)auth.getPrincipal();
        String username = userDetails.getUsername();
        Optional<User> user = userRepo.findByUsername(username);
        if (user.isEmpty()) throw new ResourceNotFoundException("wtf how did you hack my app you freaking lunatic???");
        Optional<Movie> movie =  moviesRepo.findById(rating.getMovieId());
        if (movie.isEmpty()) throw new ResourceNotFoundException("movie with id " + rating.getMovieId() + " not found");
        List<MoviesRatings> existing = ratingsRepo.findByMovieAndUser(movie.get(), user.get());
        
        if (existing.size() == 0) {
            MoviesRatings newRating = new MoviesRatings();
            newRating.setMovie(movie.get());
            newRating.setUser(user.get());
            newRating.setStars(rating.getRating());
            ratingsRepo.save(newRating);
        } else {
            existing.get(0).setStars(rating.getRating());
            ratingsRepo.save(existing.get(0));
        }
        long allMovieRatings = ratingsRepo.countByMovie(movie.get());
        if (allMovieRatings > 0 && allMovieRatings % 3 == 0) { // just pretend that this is how it supposed to be
            eventPublisher.publishEvent(new UpdateRatingEvent(this, rating.getMovieId()));
        }
        return true;
    }
}
