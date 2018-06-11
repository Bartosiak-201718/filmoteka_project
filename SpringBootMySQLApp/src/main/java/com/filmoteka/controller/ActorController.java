package com.filmoteka.controller;


import com.filmoteka.sdo.Actor;
import com.filmoteka.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ActorController {
    @Autowired
    ActorService actorService;

    @RequestMapping(value = "/actors/{id}", method = RequestMethod.GET)
    ResponseEntity<Actor> getActor(@PathVariable("id") Long id) {
        try {
            Actor actor = new Actor(actorService.getById(id));
            return new ResponseEntity<Actor>(actor, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/actors/{id}", method = RequestMethod.DELETE)
    public void deleteActor(@PathVariable("id") Long id) {
        actorService.deleteById(id);
    }

    @RequestMapping(value = "/actors", method = RequestMethod.POST)
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {
        try {
            Actor newActor = actorService.createActor(actor);
            return new ResponseEntity<Actor>(newActor, HttpStatus.OK);
        } catch (Exception c) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/actors/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Actor> updateActor(@PathVariable("id") Long id, @RequestBody Actor actor) {
        try {
            Actor actor1 = actorService.updateActor(actor, id);
            return new ResponseEntity<Actor>(actor1, HttpStatus.OK);
        } catch (Exception c) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/actors", method = RequestMethod.GET)
    public List<Actor> getAllActors() {
        return actorService.getAllActors();
    }
}
