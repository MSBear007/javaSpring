package my.spring.app.test.restapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import my.spring.app.test.restapi.model.Country;
import my.spring.app.test.restapi.repositories.CountryRepository;

@RestController
@RequestMapping(path = "/country")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @PostMapping(path = "/new")
    public @ResponseBody String postCountry(@RequestParam(value = "name", required = true)String name, 
        @RequestParam(value = "code", required = true)String code) {
        Country country = new Country(name, code);
        countryRepository.save(country);
        return "Success.";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Country> getAll(@RequestParam(value = "code", defaultValue = "007")String code) {
        return countryRepository.findAll();
    }

    @GetMapping(path="/")
    public @ResponseBody Optional<Country> getOne(@RequestParam(value="name")String name) {
        return countryRepository.findByName(name);
    }
}