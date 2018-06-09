package com.filmoteka.controller;

import org.junit.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class CountyOfProductionControllerTest {

    @Test
    public void getCountryofProduction() {
        get("/api/country/1").then().statusCode(200).assertThat()
                .body("name", equalTo("Poland"))
                .body("id", equalTo(1));
    }

    @Test
    public void deleteCountryOfProduction() {
    }

    @Test
    public void createCountryOfProduction() {
    }

    @Test
    public void updateCountryOfProduction() {
    }

    @Test
    public void getAllCountriesOfProduction() {
    }
}