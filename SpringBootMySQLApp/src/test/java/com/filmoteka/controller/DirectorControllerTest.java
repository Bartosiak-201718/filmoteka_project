package com.filmoteka.controller;

import org.junit.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class DirectorControllerTest {

    @Test
    public void getDirector() {
        get("/api/directors/1").then().statusCode(200).assertThat()
                .body("firstName", equalTo("Anna"))
                .body("lastName", equalTo("Nowa"))
                .body("id", equalTo(1));
    }

    @Test
    public void deleteDirector() {
    }

    @Test
    public void createDirector() {
    }

    @Test
    public void updateDirector() {
    }

    @Test
    public void getAllDirectors() {
    }
}