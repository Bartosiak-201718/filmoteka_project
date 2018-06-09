package com.filmoteka.controller;

import org.junit.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class AwardControllerTest {

    @Test
    public void getAward() {
        get("/api/awards/100").then().statusCode(200).assertThat()
                .body("name", equalTo("BAFTA"))
                .body("id", equalTo(100));
    }

    @Test
    public void deleteAward() {
    }

    @Test
    public void createAward() {
    }

    @Test
    public void updateAward() {
    }

    @Test
    public void getAllAwards() {
    }
}