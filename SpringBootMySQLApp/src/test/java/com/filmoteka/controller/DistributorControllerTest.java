package com.filmoteka.controller;

import org.junit.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class DistributorControllerTest {

    @Test
    public void getDistributor() {
        get("/api/distributors/11").then().statusCode(200).assertThat()
                .body("name", equalTo("Polska Film"))
                .body("id", equalTo(11));
    }

    @Test
    public void deleteDistributor() {
    }

    @Test
    public void createDistributor() {
    }

    @Test
    public void updateDistributor() {
    }

    @Test
    public void getAllDistributor() {
    }
}