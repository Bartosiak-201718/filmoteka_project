package com.filmoteka.controller;

import com.filmoteka.Exceptions.IncorrectIdException;
import com.filmoteka.sdo.Review;
import com.filmoteka.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")

public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @RequestMapping(value = "/reviews/{id}", method = RequestMethod.GET)
    ResponseEntity<Review> getReview(@PathVariable("id") Long id) {
        try {
            Review review = new Review(reviewService.getById(id));
            return new ResponseEntity<Review>(review, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/reviews/{id}", method = RequestMethod.DELETE)
    public void deleteReview(@PathVariable("id") Long id) {
        reviewService.deleteById(id);
    }

    @RequestMapping(value = "/reviews", method = RequestMethod.POST)
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        try {
            Review newReview = reviewService.createReview(review);
            return new ResponseEntity<Review>(newReview, HttpStatus.OK);
        } catch (Exception c) {
          c.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/reviews/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Review> updateReview(@PathVariable("id") Long id, @RequestBody Review review) {
        try {
            Review review1 = reviewService.updateReview(review, id);
            return new ResponseEntity<Review>(review1, HttpStatus.OK);
        } catch (IncorrectIdException c) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/reviews", method = RequestMethod.GET)
    public List<Review> getAllReview() {
        return reviewService.getAllReviews();
    }
}
