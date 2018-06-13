package com.filmoteka.controller;

import com.filmoteka.Exceptions.IncorrectIdException;
import com.filmoteka.sdo.Movie;
import com.filmoteka.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")

public class MovieController {

    @Autowired
    MovieService movieService;

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.GET)
    ResponseEntity<Movie> getMovie(@PathVariable("id") Long id) {
        try {
            Movie movie = new Movie(movieService.getById(id));
            return new ResponseEntity<Movie>(movie, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
    public void deleteMovie(@PathVariable("id") Long id) {
        movieService.deleteById(id);
    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        try {
            Movie newMovie = movieService.createMovie(movie);
            return new ResponseEntity<Movie>(newMovie, HttpStatus.OK);
        } catch (Exception c) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Movie> updateMovie(@PathVariable("id") Long id, @RequestBody Movie movie) {
        try {
            Movie movie1 = movieService.updateMovie(movie, id);
            return new ResponseEntity<Movie>(movie1, HttpStatus.OK);
        } catch (IncorrectIdException c) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
}
