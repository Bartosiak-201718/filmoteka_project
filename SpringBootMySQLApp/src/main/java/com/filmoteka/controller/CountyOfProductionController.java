package com.filmoteka.controller;

import com.filmoteka.Exceptions.IncorrectIdException;
import com.filmoteka.sdo.CountryOfProduction;
import com.filmoteka.service.CountryOfProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api")

public class CountyOfProductionController {

    @Autowired
    CountryOfProductionService countryOfProductionService;

    @RequestMapping(value = "/country/{id}", method = RequestMethod.GET)
    ResponseEntity<CountryOfProduction> getCountryofProduction(@PathVariable("id") Long id) {
        try {
            CountryOfProduction countryOfProduction = new CountryOfProduction(countryOfProductionService.getById(id));
            return new ResponseEntity<CountryOfProduction>(countryOfProduction, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/country/{id}", method = RequestMethod.DELETE)
    public void deleteCountryOfProduction(@PathVariable("id") Long id) {
        countryOfProductionService.deleteById(id);
    }

    @RequestMapping(value = "/country", method = RequestMethod.POST)
    public ResponseEntity<CountryOfProduction> createCountryOfProduction(@RequestBody CountryOfProduction countryOfProduction) {
        try {
            CountryOfProduction newCountry = countryOfProductionService.createCountryOfProduction(countryOfProduction);
            return new ResponseEntity<CountryOfProduction>(newCountry, HttpStatus.OK);
        } catch (Exception c) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/country/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CountryOfProduction> updateCountryOfProduction(@PathVariable("id") Long id, @RequestBody CountryOfProduction countryOfProduction) {
        try {
            CountryOfProduction countryOfProduction1 = countryOfProductionService.updateCountryOfProduction(countryOfProduction, id);
            return new ResponseEntity<CountryOfProduction>(countryOfProduction1, HttpStatus.OK);
        } catch (IncorrectIdException c) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/country", method = RequestMethod.GET)
    public List<CountryOfProduction> getAllCountriesOfProduction() {
        return countryOfProductionService.getAllCountryOfProductions();
    }
}
