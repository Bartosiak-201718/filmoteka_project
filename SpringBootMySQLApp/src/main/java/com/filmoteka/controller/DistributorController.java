package com.filmoteka.controller;

import com.filmoteka.sdo.Distributor;
import com.filmoteka.service.DistributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")

public class DistributorController {

    @Autowired
    DistributorService distributorService;

    @RequestMapping(value = "/distributors/{id}", method = RequestMethod.GET)
    ResponseEntity<Distributor> getDistributor(@PathVariable("id") Long id) {
        try {
            Distributor distributor = new Distributor(distributorService.getById(id));
            return new ResponseEntity<Distributor>(distributor, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/distributors/{id}", method = RequestMethod.DELETE)
    public void deleteDistributor(@PathVariable("id") Long id) {
        distributorService.deleteById(id);

    }

    @RequestMapping(value = "/distributors", method = RequestMethod.POST)
    public ResponseEntity<Distributor> createDistributor(@RequestBody Distributor distributor) {
        try {
            Distributor newDistributor = distributorService.createDistributor(distributor);
            return new ResponseEntity<Distributor>(newDistributor, HttpStatus.OK);
        } catch (Exception c) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/distributors/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Distributor> updateDistributor(@PathVariable("id") Long id, @RequestBody Distributor distributor) {
        try {
            Distributor distributor1 = distributorService.updateDistributor(distributor, id);
            return new ResponseEntity<Distributor>(distributor1, HttpStatus.OK);
        } catch (Exception c) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/distributors", method = RequestMethod.GET)
    public List<Distributor> getAllDistributor() {
        return distributorService.getAllDistributors();
    }
}
