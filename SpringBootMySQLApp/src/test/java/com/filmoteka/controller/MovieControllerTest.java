package com.filmoteka.controller;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
                .delete("/api/movies/3") //tu się cuda dzieją bo usuwa też 1
                .then()
                .statusCode(200);
    }

    @Test
    public void createMovie() {
    }

    @Test
    public void updateMovie() {
    }

    @Test
    public void getAllMovies() {
    }

    public final String descriptionOfFirstMovie = "Deep in the forests of the Pacific Northwest, isolated from society, a devoted father (Viggo Mortensen) dedicates his life to transforming his six young children into extraordinary adults. But when a tragedy strikes the family, they are forced to leave this self-created paradise and begin a journey into the outside world that challenges his idea of what it means to be a parent and brings into question everything he's taught them.";

}