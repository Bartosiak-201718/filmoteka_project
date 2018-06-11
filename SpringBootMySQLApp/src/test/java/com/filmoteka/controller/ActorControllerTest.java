package com.filmoteka.controller;

import com.filmoteka.sdo.Actor;
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

public class ActorControllerTest {

    @Test
    public void getActor() {
        get("/api/actors/1").then().statusCode(200).assertThat()
                .body("firstName", equalTo("Jason"))
                .body("lastName", equalTo("Statham"))
                .body("id", equalTo(1));
    }

    @Test
    public void createActor() throws JSONException {
        Actor actor = new Actor();
        actor.setFirstName("Jan");
        actor.setLastName("Kowalski");
        actor.setId((long) 4);

        given().body(actor)
                .when()
                .contentType(ContentType.JSON)
                .post("/api/actors");

        get("/api/actors/4").then().statusCode(200).assertThat()
                .body("firstName", equalTo("Jan"))
                .body("lastName", equalTo("Kowalski"))
                .body("id", equalTo(4));
    }

    @Test
    public void updateActor() {

        Actor actor = new Actor();
        actor.setId((long) 2);
        actor.setFirstName("Katarzyna");
        actor.setLastName("Figura");

        given().body(actor)
                .when()
                .contentType(ContentType.JSON)
                .put("/api/actors/2");

        get("/api/actors/2").then().statusCode(200).assertThat()
                .body("firstName", equalTo("Katarzyna"))
                .body("lastName", equalTo("Figura"))
                .body("id", equalTo(2));

    }

    @Test
    public void deleteActor() {
        given()
                .when()
                .delete("/api/actors/3")
                .then()
                .statusCode(200);
    }

    @Test
    public void getAllActors() {
        given().
                when().
                get("/api/actors").
                then().
                assertThat().
                body("id", hasItems(1, 2, 4))
                .body("firstName", hasItems("Jan", "Jason", "Katarzyna"))
                .body("lastName", hasItems("Figura", "Statham", "Kowalski"));
    }
}