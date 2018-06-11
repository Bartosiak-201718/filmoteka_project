package com.filmoteka.controller;

import org.junit.Test;

import com.filmoteka.sdo.CountryOfProduction;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class CountyOfProductionControllerTest {

    @Test
    public void getCountryofProduction() {
        get("/api/country/1").then().statusCode(200).assertThat()
                .body("name", equalTo("Poland"))
                .body("id", equalTo(1));
    }

    @Test
    public void deleteCountryOfProduction() {
        given()
                .when()
                .delete("/api/country/3")
                .then()
                .statusCode(200);
    }

    @Test
    public void createCountryOfProduction() {
        CountryOfProduction country = new CountryOfProduction();
        country.setName("Belgium");

        given().body(country)
                .when()
                .contentType(ContentType.JSON)
                .post("/api/country");

        get("/api/country/4").then().statusCode(200).assertThat()
                .body("name", equalTo("Belgium"))
                .body("id", equalTo(4));
    }

    @Test
    public void updateCountryOfProduction() {
        CountryOfProduction country = new CountryOfProduction();
        country.setId((long) 2);
        country.setName("Ukraine");

        given().body(country)
                .when()
                .contentType(ContentType.JSON)
                .put("/api/country/2");

        get("/api/country/2").then().statusCode(200).assertThat()
                .body("name", equalTo("Ukraine"))
                .body("id", equalTo(2));
    }

    @Test
    public void getAllCountriesOfProduction() {
    }
}