package com.filmoteka.service;

import com.filmoteka.Exceptions.IncorrectIdException;
import com.filmoteka.dao.*;
import com.filmoteka.repository.ReviewRepository;
import com.filmoteka.sdo.Actor;
import com.filmoteka.sdo.CountryOfProduction;
import com.filmoteka.sdo.Director;
import com.filmoteka.sdo.Genre;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        User tempUser = new User();
        tempUser.setCity(review.getUser().getCity());
        tempUser.setLogin(review.getUser().getLogin());
        tempUser.setPassword(review.getUser().getPassword());
        tempUser.setUserName(review.getUser().getUserName());
        tempUser.setId(review.getUser().getId());
        Movie movie = new Movie();
        movie.setDescriptionOfMovie(review.getMovie().getDescriptionOfMovie());
        movie.setDuration(review.getMovie().getDuration());
        movie.setReleaseDate(review.getMovie().getReleaseDate());
        movie.setTitle(review.getMovie().getTitle());
        movie.setId(review.getMovie().getId());
        Distributor distributor = new Distributor();
        distributor.setName(review.getMovie().getDistributor().getName());
        distributor.setId(review.getMovie().getDistributor().getId());
        movie.setDistributor(distributor);

        com.filmoteka.dao.CountryOfProduction countryOfProduction = new com.filmoteka.dao.CountryOfProduction();
        countryOfProduction.setId(review.getMovie().getCountryOfProduction().getId());
        countryOfProduction.setName(review.getMovie().getCountryOfProduction().getName());
        movie.setCountryOfProduction(countryOfProduction);

        com.filmoteka.dao.Genre genre = new com.filmoteka.dao.Genre();
        genre.setId(review.getMovie().getGenre().getId());
        genre.setName(review.getMovie().getGenre().getName());
        movie.setGenre(genre);

        com.filmoteka.dao.Director director = new com.filmoteka.dao.Director();
        director.setId(review.getMovie().getDirector().getId());
        director.setFirstName(review.getMovie().getDirector().getFirstName());
        director.setLastName(review.getMovie().getDirector().getLastName());
        movie.setDirector(director);
        com.filmoteka.dao.Actor actor1 = new com.filmoteka.dao.Actor();
        actor1.setId(review.getMovie().getActorList().get(0).getId());
        actor1.setFirstName(review.getMovie().getActorList().get(0).getFirstName());
        actor1.setLastName(review.getMovie().getActorList().get(0).getLastName());
        List<com.filmoteka.dao.Actor> actors = new ArrayList<>();
        actors.add(actor1);
        movie.setActors(actors);

        Award award = new Award();
        award.setId(review.getMovie().getAwardList().get(0).getId());
        award.setName(review.getMovie().getAwardList().get(0).getName());
        List<Award> awards = new ArrayList<>();
        movie.setAwards(awards);

        r.setUser(tempUser);
        r.setMovie(movie);
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
