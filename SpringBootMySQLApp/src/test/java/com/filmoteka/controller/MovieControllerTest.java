package com.filmoteka.controller;

import com.filmoteka.sdo.*;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class MovieControllerTest {

    @Test
    public void getMovie() {
        get("/api/movies/1").then().statusCode(200).assertThat()
                .body("title", equalTo("Capitan Fantastic"))
                .body("duration", equalTo(135))
                .body("releaseDate", equalTo(LocalDateTime.of(2016,06,23,0,0).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
                .body("descriptionOfMovie", equalTo(descriptionOfFirstMovie));
    }

    @Test
    public void deleteMovie() {
        given()
                .when()
                .delete("/api/movies/3")
                .then()
                .statusCode(200);
    }

    @Test
    public void createMovie() {
        Movie movie = new Movie();
        CountryOfProduction countryOfProduction = new CountryOfProduction();
        countryOfProduction.setId((long) 2);
        countryOfProduction.setName("France");

        Genre genre = new Genre();
        genre.setId((long) 1);
        genre.setName("drama");

        Distributor distributor = new Distributor();
        distributor.setId((long) 11);
        distributor.setName("Polska Film");

        Director director = new Director();
        director.setId((long) 2);
        director.setFirstName("Tom");
        director.setLastName("Lee");

        Actor actor = new Actor();
        actor.setId((long) 3);
        actor.setFirstName("Matylda");
        actor.setLastName("Cisza");
        List<Actor> actorList = new ArrayList<>();
        actorList.add(actor);

        Award award = new Award();
        award.setId((long)2);
        award.setName("Oscar 2018");

        List<Award> awards = new ArrayList<>();
        awards.add(award);

        fillMovieData(movie,
                "Shape of Water",
                "2018-02-23T00:00:00",
                "At a top secret research facility in the 1960s, a lonely janitor forms a unique relationship with an amphibious creature that is being held in captivity.",
                119,
                countryOfProduction,
                genre,
                distributor,
                director,
                actorList,
                awards);

        given().body(movie)
                .when()
                .contentType(ContentType.JSON)
                .post("/api/movies");

        get("/api/movies/4").then().statusCode(200).assertThat()
                .body("id",equalTo(4))
                .body("title",equalTo("Shape of Water"))
                .body("duration", equalTo(119))
                .body("descriptionOfMovie", equalTo("At a top secret research facility in the 1960s, a lonely janitor forms a unique relationship with an amphibious creature that is being held in captivity."))
                .body("releaseDate", equalTo("2018-02-23T00:00:00"));
    }

    @Test
    public void updateMovie() {
        Movie movie = new Movie();
        movie.setId((long) 1);

        CountryOfProduction countryOfProduction = new CountryOfProduction();
        countryOfProduction.setId((long) 2);
        countryOfProduction.setName(get("/api/country/2").path("name"));

        Genre genre = new Genre();
        genre.setId((long) 3);
        genre.setName(get("/api/genres/3").path("name"));

        Distributor distributor = new Distributor();
        distributor.setId((long) 11);
        distributor.setName(get("/api/distributors/11").path("name"));

        Director director = new Director();
        director.setId((long) 1);
        director.setFirstName(get("/api/directors/1").path("firstName"));
        director.setLastName(get("/api/directors/1").path("lastName"));

        fillMovieData(movie,
                get("/api/movies/1").path("title"),
                get("/api/movies/1").path("releaseDate"),
                get("/api/movies/1").path("descriptionOfMovie"),
                136, //CHANGED VALUE -> ORIGINALLY 135
                countryOfProduction,
                genre,
                distributor,
                director,
                get("/api/movies/1").path("actorList"),
                get("/api/movies/1").path("awardList"));

        given().body(movie)
                .when()
                .contentType(ContentType.JSON)
                .put("/api/movies/1");

        get("/api/movies/1").then().statusCode(200).assertThat()
                .body("id",equalTo(1))
                .body("title",equalTo("Capitan Fantastic"))
                .body("duration", equalTo(136))
                .body("descriptionOfMovie",  equalTo(descriptionOfFirstMovie))
                .body("releaseDate", equalTo("2016-06-23T00:00:00"));

    }

    @Test
    public void getAllMovies() {
    }

    public void fillMovieData(Movie movie,String title, String dt,String description, int duration,
                              CountryOfProduction countryOfProduction,Genre genre,Distributor distributor,Director director,List<Actor> actorList, List<Award> awards){
        movie.setTitle(title);
        movie.setReleaseDate(LocalDateTime.parse(LocalDateTime.parse(dt).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString()));
        movie.setDescriptionOfMovie(description);
        movie.setDuration(duration);
        movie.setCountryOfProduction(countryOfProduction);
        movie.setGenre(genre);
        movie.setDistributor(distributor);
        movie.setDirector(director);
        movie.setAwardList(awards);
        movie.setActorList(actorList);
    }

    public final String descriptionOfFirstMovie = "Deep in the forests of the Pacific Northwest, isolated from society, a devoted father (Viggo Mortensen) dedicates his life to transforming his six young children into extraordinary adults. But when a tragedy strikes the family, they are forced to leave this self-created paradise and begin a journey into the outside world that challenges his idea of what it means to be a parent and brings into question everything he's taught them.";

}