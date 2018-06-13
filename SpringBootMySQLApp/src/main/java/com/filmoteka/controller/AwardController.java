package com.filmoteka.controller;

import com.filmoteka.Exceptions.IncorrectIdException;
import com.filmoteka.sdo.Award;
import com.filmoteka.service.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api")

public class AwardController {

    @Autowired
    AwardService awardService;

    @RequestMapping(value = "/awards/{id}", method = RequestMethod.GET)
    ResponseEntity<Award> getAward(@PathVariable("id") Long id) {
        try {
            Award award = new Award(awardService.getById(id));
            return new ResponseEntity<Award>(award, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/awards/{id}", method = RequestMethod.DELETE)
    public void deleteAward(@PathVariable("id") Long id) {
        awardService.deleteById(id);
    }

    @RequestMapping(value = "/awards", method = RequestMethod.POST)
    public ResponseEntity<Award> createAward(@RequestBody Award award) {
        try {
            Award newAward = awardService.createAward(award);
            return new ResponseEntity<Award>(newAward, HttpStatus.OK);
        } catch (Exception c) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/awards/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Award> updateAward(@PathVariable("id") Long id, @RequestBody Award award) {
        try {
            Award award1 = awardService.updateAward(award, id);
            return new ResponseEntity<Award>(award1, HttpStatus.OK);
        } catch (IncorrectIdException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/awards", method = RequestMethod.GET)
    public List<Award> getAllAwards() {
        return awardService.getAllAwards();
    }

}
