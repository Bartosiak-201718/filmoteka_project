package com.filmoteka.controller;

import com.filmoteka.sdo.Director;
import com.filmoteka.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api")

public class DirectorController {
    @Autowired
    DirectorService directorService;

    @RequestMapping(value = "/directors/{id}", method = RequestMethod.GET)
    ResponseEntity<Director> getDirector(@PathVariable("id") Long id) {
        try {
            Director director = new Director(directorService.getById(id));
            return new ResponseEntity<Director>(director, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/directors/{id}", method = RequestMethod.DELETE)
    public void deleteDirector(@PathVariable("id") Long id) {
        directorService.deleteById(id);
    }

    @RequestMapping(value = "/directors", method = RequestMethod.POST)
    public ResponseEntity<Director> createDirector(@RequestBody Director director) {
        try {
            Director newDirector = directorService.createDirector(director);
            return new ResponseEntity<Director>(newDirector, HttpStatus.OK);
        } catch (Exception c) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/directors/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Director> updateDirector(@PathVariable("id") Long id, @RequestBody Director director) {
        try {
            Director director1 = directorService.updateDirector(director, id);
            return new ResponseEntity<Director>(director1, HttpStatus.OK);
        } catch (Exception c) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/directors", method = RequestMethod.GET)
    public List<Director> getAllDirectors() {
        return directorService.getAllDirectors();
    }

}
