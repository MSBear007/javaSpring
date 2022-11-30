package my.spring.app.test.restapi.controller;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import my.spring.app.test.dto.RatingDto;
import my.spring.app.test.exceptions.ResourceNotFoundException;
import my.spring.app.test.restapi.service.RatingsService;

@RestController
@RequestMapping(path = "/${v1Api}/post_rating")
public class RatingController {
    
    @Autowired
    private RatingsService service;

    @PostMapping(path = "/")
    public @ResponseBody boolean postRating(@Valid @ModelAttribute(name = "rating_") RatingDto rating, BindingResult result) throws ResourceNotFoundException{
        if (result.hasErrors()) throw new ValidationException(result.getAllErrors().toString());
        return service.addRating(rating);
    }
}
