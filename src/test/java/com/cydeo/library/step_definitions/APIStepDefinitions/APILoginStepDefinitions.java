package com.cydeo.library.step_definitions.APIStepDefinitions;

import com.cydeo.library.utilities.APIUtilities;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;

import io.restassured.response.*;
import org.junit.Assert;
import org.junit.Test;


import static io.restassured.RestAssured.*;

public class APILoginStepDefinitions extends APITestBase {

    String token = "";
    Response response;

    @Given("get token with {string} and {string}")
    public void get_token_with_and(String username, String password) {

        token = APIUtilities.getToken(username, password);
        response = APIUtilities.getResponse(username, password);
    }


    @Then("status code should be {int}")
    public void status_code_should_be(int statusCode) {
        Assert.assertEquals(statusCode, response.statusCode());
    }



    @Then("response body returns error {string} and status code should be {int}")
    public void response_body_returns_error_and_status_code_should_be(String body, Integer statusCode) {

        String errorMessage = response.path("error");
        System.out.println("errorMessage = " + errorMessage);
        System.out.println("response.statusCode() = " + response.statusCode());

        Assert.assertEquals(body, response.path("error"));
        Assert.assertEquals((int) statusCode, response.statusCode());

    }


}

/*

//    @DisplayName("Test getToken method")
    @Test
    public void testGetToken(){
        String token = LibraryAPIUtilities.getToken("librarian1@library", "rs4BNN9G");

        System.out.println("token = " + token);

        given().header("x-library-token", token)
                .and().accept(ContentType.JSON)
                .when().get("https://library1.cydeo.com/rest/v1/get_all_users")
                .then().statusCode(200)
                .log().all();

    }
*/