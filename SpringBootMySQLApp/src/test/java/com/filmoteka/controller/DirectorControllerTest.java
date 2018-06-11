package com.filmoteka.controller;

import com.filmoteka.sdo.Director;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
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
        given()
                .when()
                .delete("/api/directors/3")
                .then()
                .statusCode(200);
    }

    @Test
    public void createDirector() throws JSONException {
        Director director = new Director();
        director.setFirstName ("Ann");
        director.setLastName ("Max");

        given().body (director)
                .when ()
                .contentType (ContentType.JSON)
                .post ("/api/directors");

        get("/api/directors/4").then().statusCode(200).assertThat()
                .body("firstName", equalTo("Ann"))
                .body("lastName", equalTo("Max"))
                .body("id", equalTo(4));
    }

    @Test
    public void updateDirector() {
        Director director = new Director();
        director.setId((long) 2);
        director.setFirstName ("Martin");
        director.setLastName ("Maxx");

        given().body (director)
                .when ()
                .contentType (ContentType.JSON)
                .put ("/api/directors/2");

        get("/api/directors/2").then().statusCode(200).assertThat()
                .body("firstName", equalTo("Martin"))
                .body("lastName", equalTo("Maxx"))
                .body("id", equalTo(2));
    }

    @Test
    public void getAllDirectors() {
    }
}