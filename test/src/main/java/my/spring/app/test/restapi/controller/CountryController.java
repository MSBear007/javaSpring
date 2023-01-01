package my.spring.app.test.restapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import my.spring.app.test.restapi.model.Country;
import my.spring.app.test.restapi.repositories.CountryRepository;

@RestController
@RequestMapping(path = "/${v1Api}/country")
@CrossOrigin(origins = "http://localhost:3000")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    /**
     * POST method
     * @param name name of the country as request parameter
     * @param code code of the country as request parameter
     */
    @PostMapping(path = "/")
    public @ResponseBody String postCountry(@RequestParam(value = "name", required = true)String name, 
        @RequestParam(value = "code", required = true)String code) {
        Country country = new Country(name, code);
        countryRepository.save(country);
        return "Success.";
    }

    /**
     * GET all
     * @return all countries
     * @see {@link my.spring.app.test.restapi.model.Country}
     */
    @GetMapping(path = "/")
    public @ResponseBody Iterable<Country> getAll() {
        return countryRepository.findAll();
    }

    /**
     * GET by name
     * @param name - name of the country as URL path variable
     * @see {@link my.spring.app.test.restapi.model.Country}
     */
    @GetMapping(path="/{name}")
    public @ResponseBody Optional<Country> getOne(@PathVariable String name) {
        return countryRepository.findByName(name);
    }
}