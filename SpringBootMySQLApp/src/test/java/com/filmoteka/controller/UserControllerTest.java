package com.filmoteka.controller;

import com.filmoteka.sdo.User;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class UserControllerTest {

    @Test
    public void getUser() {
        get("/api/users/1").then().statusCode(200).assertThat()
                .body("login", equalTo("admin"))
                .body("password", equalTo("1234"))
                .body("userName", equalTo("Adam Nowak"))
                .body("city", equalTo("Warsaw"))
                .body("id", equalTo(1));
    }

    @Test
    public void deleteUser() {
        given()
                .when()
                .delete("/api/users/3")
                .then()
                .statusCode(200);
    }

    @Test
    public void createUser() {
        User user = new User();
        user.setCity("Barcelona");
        user.setLogin("magicznykrzysz");
        user.setPassword("megatajne123");
        user.setUserName("Krzysztof Dam");

        given().body(user)
                .when()
                .contentType(ContentType.JSON)
                .post("/api/users");

        get("/api/users/4").then().statusCode(200).assertThat()
                .body("login",equalTo("magicznykrzysz"))
                .body("password",equalTo("megatajne123"))
                .body("city", equalTo("Barcelona"))
                .body("id", equalTo(4))
                .body("userName", equalTo("Krzysztof Dam"));

    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId((long) 2);
        user.setPassword("ojejku");
        user.setUserName("Joe Grams");
        user.setLogin("kaskaMalpa");
        user.setCity("London");
        given().body(user)
                .when()
                .contentType(ContentType.JSON)
                .put("/api/users/2");

        get("/api/users/2").then().statusCode(200).assertThat()
                .body("password", equalTo("ojejku"))
                .body("id",equalTo(2));
    }

    @Test
    public void getAllUsers() {
    }
}