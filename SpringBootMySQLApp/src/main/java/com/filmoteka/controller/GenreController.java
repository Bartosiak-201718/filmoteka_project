package com.filmoteka.controller;

import com.filmoteka.Exceptions.IncorrectIdException;
import com.filmoteka.sdo.Genre;
import com.filmoteka.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")

public class GenreController {

    @Autowired
    GenreService genreService;

    @RequestMapping(value = "/genres/{id}", method = RequestMethod.GET)
    ResponseEntity<Genre> getGenre(@PathVariable("id") Long id) {
        try {
            Genre distributor = new Genre(genreService.getById(id));
            return new ResponseEntity<Genre>(distributor, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/genres/{id}", method = RequestMethod.DELETE)
    public void deleteGenre(@PathVariable("id") Long id) {
        genreService.deleteById(id);
    }

    @RequestMapping(value = "/genres", method = RequestMethod.POST)
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        try {
            Genre newGenre = genreService.createGenre(genre);
            return new ResponseEntity<Genre>(newGenre, HttpStatus.OK);
        } catch (IllegalArgumentException  c) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/genres/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Genre> updateGenre(@PathVariable("id") Long id, @RequestBody Genre genre) {
        try {
            Genre genre1 = genreService.updateGenre(genre, id);
            return new ResponseEntity<Genre>(genre1, HttpStatus.OK);
        } catch (IncorrectIdException c) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/genres", method = RequestMethod.GET)
    public List<Genre> getAllGenre() {
        return genreService.getAllGenres();
    }

}
