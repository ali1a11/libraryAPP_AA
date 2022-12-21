package com.cydeo.library.step_definitions.APIStepDefinitions;

import com.cydeo.library.utilities.ConfigurationReader;
import com.cydeo.library.utilities.LibraryAPIUtilities;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class AddBookStepDefinitions {


    Response response;

    @Given("librarian makes POST request to add book")
    public void librarian_makes_post_request_to_add_book(Map<String, String> bookInfo) {

        String librarianToken = LibraryAPIUtilities.getToken(ConfigurationReader.getProperty("librarian_username"), ConfigurationReader.getProperty("librarian_password"));

        response = given().header("x-library-token", librarianToken)
                .and().accept(ContentType.JSON)
                .formParams(bookInfo)
                .when().post("http://library1.cydeo.com/rest/v1/add_book")
                .prettyPeek();
    }



    @Then("user gets the response {string} and status code should be {int}")
    public void user_gets_the_response_and_status_code_should_be(String messageText, int statusCode) {

        Assert.assertEquals(statusCode, response.statusCode());
        Assert.assertEquals(messageText, response.path("error"));
    }

    @Then("user gets the response {string} as {string} and status code should be {int}")
    public void user_gets_the_response_as_and_status_code_should_be(String message, String messageText, int statusCode) {

        Assert.assertEquals(statusCode, response.statusCode());
        Assert.assertEquals(messageText, response.path(message));

    }



    @Given("student makes POST request to add book")
    public void student_makes_post_request_to_add_book(Map<String, String> bookInfo) {
        String studentToken = LibraryAPIUtilities.getToken(ConfigurationReader.getProperty("student_username"), ConfigurationReader.getProperty("student_password"));

        System.out.println("studentToken = " + studentToken);

        response = given().header("x-library-token", studentToken)
                .and().accept(ContentType.JSON)
                .formParams(bookInfo)
                .when().post("http://library1.cydeo.com/rest/v1/add_book")
                .prettyPeek();

    }





}
