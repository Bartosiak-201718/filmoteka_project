package com.filmoteka.sdo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal on 20.04.2018.
 */
public class Review {

    public Review(Long id, String userReview) {
        this.id = id;
        this.userReview = userReview;
    }

    public Review(com.filmoteka.dao.Review review) {
        this.userReview = review.getUserReview();
        this.id = review.getId();
        User user = new User();
        user.setCity(review.getUser().getCity());
        user.setId(review.getUser().getId());
        user.setLogin(review.getUser().getLogin());
        user.setPassword(review.getUser().getPassword());
        user.setUserName(review.getUser().getUserName());

        this.user = user;

        Movie movie = new Movie();

        CountryOfProduction countryOfProduction = new CountryOfProduction();
        countryOfProduction.setId(review.getMovie().getCountryOfProduction().getId());
        countryOfProduction.setName(review.getMovie().getCountryOfProduction().getName());
        movie.setCountryOfProduction(countryOfProduction);

        movie.setDescriptionOfMovie(review.getMovie().getDescriptionOfMovie());

        Director director = new Director();
        director.setFirstName(review.getMovie().getDirector().getFirstName());
        director.setLastName(review.getMovie().getDirector().getLastName());
        director.setId(review.getMovie().getDirector().getId());
        movie.setDirector(director);


        Distributor distributor = new Distributor();
        distributor.setId(review.getMovie().getDistributor().getId());
        distributor.setName(review.getMovie().getDistributor().getName());
        movie.setDistributor(distributor);

        movie.setDuration(review.getMovie().getDuration());

        movie.setId(review.getMovie().getId());

        movie.setReleaseDate(review.getMovie().getReleaseDate());

        movie.setTitle(review.getMovie().getTitle());

        Genre genre = new Genre();
        genre.setId(review.getMovie().getGenre().getId());
        genre.setName(review.getMovie().getGenre().getName());
        movie.setGenre(genre);

        List<Actor> actorList = new ArrayList<>();
        for(int i = 0; i < review.getMovie().getActors().size(); i++){
            Actor actor = new Actor();
            actor.setFirstName(review.getMovie().getActors().get(i).getFirstName());
            actor.setId(review.getMovie().getActors().get(i).getId());
            actor.setLastName(review.getMovie().getActors().get(i).getLastName());
            actorList.add(actor);
        }
        movie.setActorList(actorList);

        List<Award> awardList = new ArrayList<>();
        for(int i = 0; i < review.getMovie().getAwards().size(); i++){
            Award award = new Award();
            award.setName(review.getMovie().getAwards().get(i).getName());
            award.setId(review.getMovie().getAwards().get(i).getId());
            awardList.add(award);
        }
        movie.setAwardList(awardList);

        this.movie = movie;
    }

    public Review() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserReview() {
        return userReview;
    }

    public void setUserReview(String userReview) {
        this.userReview = userReview;
    }

    private Long id;
    private String userReview;

    ////////////////////////////////////

    private Movie movie;
    private User user;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Review(Long id, String userReview, Movie movie, User user) {
        this.id = id;
        this.userReview = userReview;
        this.movie = movie;
        this.user = user;
    }
}
