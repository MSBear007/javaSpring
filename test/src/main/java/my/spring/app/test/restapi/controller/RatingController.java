package my.spring.app.test.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import my.spring.app.test.exceptions.ResourceNotFoundException;
import my.spring.app.test.restapi.service.RatingsService;

@RestController
@RequestMapping(path = "/${v1Api}/post_rating")
public class RatingController {
    
    @Autowired
    private RatingsService service;

    @PostMapping(path = "/")
    public @ResponseBody boolean postRating(@ModelAttribute(name = "movie_id") long movie_id, 
        @ModelAttribute(name = "rating") int rating) throws ResourceNotFoundException{
        return service.addRating(movie_id, rating);
    }
}
