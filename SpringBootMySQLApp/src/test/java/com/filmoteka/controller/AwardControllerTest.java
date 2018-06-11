package com.filmoteka.controller;

import org.junit.Test;

import com.filmoteka.sdo.Award;
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

public class AwardControllerTest {

    @Test
    public void getAward() {
        get("/api/awards/1").then().statusCode(200).assertThat()
                .body("name", equalTo("BAFTA"))
                .body("id", equalTo(1));
    }

    @Test
    public void deleteAward() {
        given().
                when()
                .delete("/api/awards/3")
                .then()
                .statusCode(200);
    }

    @Test
    public void createAward() throws JSONException {
        Award award = new Award();
        award.setName("MTV Music Award 2016");

        given().body(award)
                .when()
                .contentType(ContentType.JSON)
                .post("/api/awards");

        get("/api/awards/4").then().statusCode(200).assertThat()
                .body("name", equalTo("MTV Music Award 2016"))
                .body("id", equalTo(4));
    }

    @Test
    public void updateAward() {
        Award award = new Award();
        award.setId((long) 2);
        award.setName("Gotham Awards");

        given().body(award)
                .when()
                .contentType(ContentType.JSON)
                .put("/api/awards/2");

        get("/api/awards/2").then().statusCode(200).assertThat()
                .body("name", equalTo("Gotham Awards"))
                .body("id", equalTo(2));
    }

    @Test
    public void getAllAwards() {
    }
}