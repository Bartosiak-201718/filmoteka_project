package com.filmoteka.controller;

import com.filmoteka.sdo.Award;
import com.filmoteka.sdo.Genre;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.*;

public class GenreControllerTest {

    @Test
    public void getGenre() {
        get("/api/genres/1").then().statusCode(200).assertThat()
                .body("name", equalTo("drama"))
                .body("id", equalTo(1));
    }

    @Test
    public void deleteGenre() {
        given()
                .when()
                .delete("/api/genres/3")
                .then()
                .statusCode(200);
    }

    @Test
    public void createGenre() {
        Genre genre = new Genre();
        genre.setName("Comedy");

        given().body(genre)
                .when()
                .contentType(ContentType.JSON)
                .post("/api/genres");

        get("/api/genres/5").then().statusCode(200).assertThat()
                .body("name", equalTo("Comedy"))
                .body("id", equalTo(5));
    }

    @Test
    public void updateGenre() {
        Genre genre = new Genre();
        genre.setId((long) 2);
        genre.setName("SuperHero");


        given().body(genre)
                .when()
                .contentType(ContentType.JSON)
                .put("/api/genres/2");

        get("/api/genres/2").then().statusCode(200).assertThat()
                .body("name", equalTo("SuperHero"))
                .body("id", equalTo(2));
    }

    @Test
    public void getAllGenre() {
        given().
                when().
                get("/api/genres").
                then().
                assertThat().
                body("id", hasItems(1, 4))
                .body("name", hasItems("drama", "fantasy"));
    }
}