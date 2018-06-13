package com.filmoteka.controller;

import com.filmoteka.dao.Movie;
import com.filmoteka.sdo.*;
import com.filmoteka.service.MovieService;
import com.filmoteka.service.UserService;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class ReviewControllerTest {

    @Test
    public void getReview() {
        get("/api/reviews/1").then().statusCode(200).assertThat()
                .body("userReview", equalTo(firstReview))
                .body("id", equalTo(1));
    }

    @Test
    public void deleteReview() {
        given()
                .when()
                .delete("/api/reviews/3")
                .then()
                .statusCode(200);
    }

    @Test
    public void createReview() {
        User user = new User();
        user.setCity("Warsaw");
        user.setLogin("admin");
        user.setPassword("1234");
        user.setUserName("Adam Nowak");
        user.setId((long) 1);

        Review review = new Review();
        review.setUserReview("Good good good very good very good");
        review.setMovie(getFirstMovie());
        review.setUser(user);

        given().body(review)
                .when()
                .contentType(ContentType.JSON)
                .post("/api/reviews");

        get("/api/reviews/4").then().statusCode(200).assertThat()
                .body("id",equalTo(4))
                .body("userReview",equalTo("Good good good very good very good"));
    }

    @Test
    public void updateReview() {
        User user = new User();
        user.setCity("London");
        user.setLogin("kaskaMalpa");
        user.setPassword("haslo123");
        user.setUserName("Joe Grams");
        user.setId((long) 2);


        Review review = new Review();
        review.setId((long) 1);
        review.setUserReview("Very bad, no one should see that");
        review.setMovie(getFirstMovie());
        review.setUser(user);

        given().body(review)
                .when()
                .contentType(ContentType.JSON)
                .put("/api/reviews/1");

        get("/api/reviews/1").then().statusCode(200).assertThat()
                .body("id",equalTo(1))
                .body("userReview",equalTo("Very bad, no one should see that"));

    }

    @Test
    public void getAllReview() {
    }

    public com.filmoteka.sdo.Movie getFirstMovie(){
        com.filmoteka.sdo.Movie movie = new com.filmoteka.sdo.Movie();
        movie.setId((long) 1);

        movie.setTitle(get("/api/movies/1").path("title"));
        String dt = get("/api/movies/1").path("releaseDate");
        movie.setReleaseDate(LocalDateTime.parse(LocalDateTime.parse(dt).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString()));
        movie.setDescriptionOfMovie(get("/api/movies/1").path("descriptionOfMovie"));
        movie.setDuration(get("/api/movies/1").path("duration"));

        CountryOfProduction countryOfProduction = new CountryOfProduction();
        countryOfProduction.setId((long) 1);
        countryOfProduction.setName(get("/api/country/1").path("name"));
        movie.setCountryOfProduction(countryOfProduction);

        Genre genre = new Genre();
        genre.setId((long) 1);
        genre.setName(get("/api/genres/1").path("name"));
        movie.setGenre(genre);

        Distributor distributor = new Distributor();
        distributor.setId((long) 11);
        distributor.setName(get("/api/distributors/11").path("name"));
        movie.setDistributor(distributor);

        Director director = new Director();
        director.setId((long) 1);
        director.setFirstName(get("/api/directors/1").path("firstName"));
        director.setLastName(get("/api/directors/1").path("lastName"));
        movie.setDirector(director);
        movie.setAwardList(get("/api/movies/1").path("awardList"));
        movie.setActorList(get("/api/movies/1").path("actorList"));
        return movie;
    }

    public final String firstReview = "This is really a movie for upper-middle class hipsters who once fancied themselves firebrands and status quo-challengers in college, but now consider only buying organic food at Whole Foods and not vaccinating their kids to be radical acts.";
}