package com.filmoteka.sdo;

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
