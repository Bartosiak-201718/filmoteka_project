package com.filmoteka.controller;

import com.filmoteka.sdo.Distributor;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
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
        given()
                .when()
                .delete("/api/distributors/13")
                .then()
                .statusCode(200);
    }

    @Test
    public void createDistributor() {
        Distributor distributor = new Distributor();
        distributor.setName("Warner Bros");

        given().body(distributor)
                .when()
                .contentType(ContentType.JSON)
                .post("/api/distributors");

        get("/api/distributors/1").then().statusCode(200).assertThat()
                .body("name", equalTo("Warner Bros"))
                .body("id", equalTo(1));
    }

    @Test
    public void updateDistributor() {
        Distributor distributor = new Distributor();
        distributor.setId((long) 12);
        distributor.setName("MCU");

        given().body(distributor)
                .when()
                .contentType(ContentType.JSON)
                .put("/api/distributors/12");

        get("/api/distributors/12").then().statusCode(200).assertThat()
                .body("name", equalTo("MCU"))
                .body("id", equalTo(12));
    }

    @Test
    public void getAllDistributor() {
        given()
                .when()
                .get("/api/distributors")
                .then()
                .assertThat()
                .body("id", hasItems(11, 12, 13))
                .body("name", hasItems("Polska Film", "MCU", "Disney"));
    }
}