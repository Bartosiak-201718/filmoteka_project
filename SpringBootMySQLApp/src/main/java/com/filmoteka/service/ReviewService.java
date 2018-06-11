package com.filmoteka.service;

import com.filmoteka.Exceptions.IncorrectIdException;
import com.filmoteka.dao.Review;
import com.filmoteka.repository.ReviewRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    final Logger logger = LogManager.getLogger(ReviewService.class);

    @Autowired
    ReviewRepository reviewRepository;

    public Review getById(Long id) {
        return reviewRepository.getOne(id);
    }


    public void deleteById(Long id) {
        try {
            reviewRepository.deleteById(id);
        } catch (Exception e) {
            logger.warn("IllegalArgumentException in application");
        }
    }

    public com.filmoteka.sdo.Review createReview(com.filmoteka.sdo.Review review) {
        Review r = new Review();
        r.setUserReview(review.getUserReview());
        r = reviewRepository.save(r);
        com.filmoteka.sdo.Review saved = new com.filmoteka.sdo.Review(r);
        return saved;
    }

    public com.filmoteka.sdo.Review updateReview(com.filmoteka.sdo.Review review, Long id) throws IncorrectIdException {

        Review review1 = new Review();
        if (!review.getId().equals(id)) {
            throw new IncorrectIdException("Wrong id!");
        }
        review1 = reviewRepository.getOne(id);
        review1.setUserReview(review.getUserReview());
        review1 = reviewRepository.save(review1);
        com.filmoteka.sdo.Review saved = new com.filmoteka.sdo.Review(review1);
        return saved;
    }

    public List<com.filmoteka.sdo.Review> getAllReviews() {
        List<com.filmoteka.sdo.Review> reviewList = new ArrayList<>();
        for (Review reviewDao : reviewRepository.findAll()) {
            com.filmoteka.sdo.Review review = new com.filmoteka.sdo.Review(reviewDao);
            reviewList.add(review);
        }
        return reviewList;
    }
}
