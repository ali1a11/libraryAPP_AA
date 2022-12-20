package com.cydeo.library.utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LibraryAPIUtilities {

    public static String getToken (String email, String password){

        String token = given()
                .and().accept(ContentType.JSON)
                .and().formParam("email", email)
                .and().formParam("password", password)
                .when().post("https://library1.cydeo.com/rest/v1/login")
                .body().path("token");

        return token;
    }


    private static Response response;

    public static Response getResponse(String email, String password) {

        response = given()
                .and().accept(ContentType.JSON)
                .and().formParam("email", email)
                .and().formParam("password", password)
                .when().post("https://library1.cydeo.com/rest/v1/login");
        return response;
        }

    }









