package com.filmoteka.controller;

import org.junit.Test;

import static io.restassured.RestAssured.get;
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
    }

    @Test
    public void createUser() {
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void getAllUsers() {
    }
}