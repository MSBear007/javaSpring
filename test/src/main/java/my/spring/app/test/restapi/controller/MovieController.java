package my.spring.app.test.restapi.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import my.spring.app.test.dto.GetMovieDto;
import my.spring.app.test.dto.MovieDto;
import my.spring.app.test.exceptions.ResourceNotFoundException;
import my.spring.app.test.restapi.model.Movie;
import my.spring.app.test.restapi.service.MovieService;

@RestController
@RequestMapping(path = "/${v1Api}/movie")
public class MovieController {

    @Autowired
    private MovieService service;

    @GetMapping(path="/id/{id}")
    public @ResponseBody GetMovieDto getMovieById(@PathVariable long id) throws ResourceNotFoundException, IOException {
        return service.getMovieById(id);
    }

    @PostMapping(path="/add")
    public Movie postMovie(@ModelAttribute("movie") MovieDto movieDto) throws IOException {
        return service.addMovie(movieDto);
    }
}
