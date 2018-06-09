package com.filmoteka.controller;

import org.junit.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
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
    }

    @Test
    public void createGenre() {
    }

    @Test
    public void updateGenre() {
    }

    @Test
    public void getAllGenre() {
    }
}