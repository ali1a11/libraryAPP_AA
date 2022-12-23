package com.cydeo.library.step_definitions.APIStepDefinitions;


import org.junit.jupiter.api.BeforeAll;
import io.restassured.RestAssured;

public class APITestBase {

    @BeforeAll
    public static void init() {
        //save baseURI inside this variable so that we don't need to type each html method
        RestAssured.baseURI = "https://library1.cydeo.com";

    }

}
