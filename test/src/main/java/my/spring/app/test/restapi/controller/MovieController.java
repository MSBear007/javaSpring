package my.spring.app.test.restapi.controller;

import java.io.IOException;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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

    @GetMapping(path="/{id}")
    public @ResponseBody GetMovieDto getMovieById(@PathVariable long id) throws ResourceNotFoundException, IOException {
        return service.getMovieById(id);
    }

    @PostMapping(path="/")
    public Movie postMovie(@Valid @ModelAttribute("movie") MovieDto movieDto, BindingResult result) throws IOException {
        if (result.hasErrors()) throw new ValidationException(result.getAllErrors().toString());
        return service.addMovie(movieDto);
    }
}
