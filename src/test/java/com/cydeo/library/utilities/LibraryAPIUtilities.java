package com.cydeo.library.utilities;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

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

        public static Map<String, Object> createUser (int userGroup) {
            Faker faker = new Faker();
            Map<String, Object> user = new HashMap<>();

            String fullName = faker.name().fullName();
            String userEmail = faker.name().username() + "@library";
            String userPassword = faker.bothify("#?#?##??");
            String userAddress = faker.address().fullAddress();

            user.put("full_name", fullName);
            user.put("email", userEmail);
            user.put("password", userPassword);
            user.put("user_group_id", userGroup);
            user.put("status", "ACTIVE");
            user.put("start_date", "2023-01-01");
            user.put("end_date", "2023-12-31");
            user.put("address", userAddress);

            return user;

        }


    }









