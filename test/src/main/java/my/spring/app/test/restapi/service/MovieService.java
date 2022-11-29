package my.spring.app.test.restapi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import my.spring.app.test.dto.GetMovieDto;
import my.spring.app.test.dto.MovieDto;
import my.spring.app.test.exceptions.ResourceNotFoundException;
import my.spring.app.test.restapi.model.Movie;
import my.spring.app.test.restapi.model.MoviesRatings;
import my.spring.app.test.restapi.repositories.MovieRepository;

@Service
@Transactional
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    private final String pathToPosters = "C:\\Users\\bluff\\Desktop\\javaSpring\\test\\static\\img\\movies-posters\\";


    private Movie newMovie(MovieDto dto) throws IOException {
        Movie movie = new Movie();
        movie.setTitle(dto.getTitle());
        movie.setYear(dto.getYear());
        MultipartFile poster = dto.getPoster();
        if (!poster.isEmpty()) {
            byte[] bytes = poster.getBytes();
            Path path = Paths.get(pathToPosters + poster.getOriginalFilename());
            Files.write(path, bytes);
            movie.setPosterPath(poster.getOriginalFilename());
        }
        return movie;
    }

    public Movie addMovie(MovieDto dto) throws IOException {
        return movieRepository.save(newMovie(dto));
    }

    public GetMovieDto getMovieById(long id) throws ResourceNotFoundException, IOException {
        Optional<Movie> res = movieRepository.findById(id);

        if (res.isPresent()) {
            GetMovieDto dto = new GetMovieDto();
            dto.setRating(res.get().getRating());
            dto.setTitle(res.get().getTitle());
            dto.setYear(res.get().getYear());
            byte[] content = Files.readAllBytes(Paths.get(pathToPosters + res.get().getPosterPath()));
            dto.setPoster(content);
            return dto;
        }
        else throw new ResourceNotFoundException("no movie with id " + id);

    }

    public void updateRating(long id) throws ResourceNotFoundException {
        Optional<Movie> res = movieRepository.findById(id);

        if (res.isPresent()) {
            List<MoviesRatings> ratings = res.get().getRatings();
            // very slow and unoptimшzed, but i don't care
            double average = ratings.stream().mapToDouble(rating -> {
                return (double)(rating.getStars());
            }).average().getAsDouble();
            res.get().setRating(average);
            movieRepository.save(res.get());
        }
        else throw new ResourceNotFoundException("no movie with id " + id);
    } 
}