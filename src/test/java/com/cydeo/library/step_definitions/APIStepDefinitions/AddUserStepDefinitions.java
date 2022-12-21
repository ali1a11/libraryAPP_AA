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

public class AddUserStepDefinitions {

    Response response;

    @Given("librarian adds new {string} user")
    public void librarian_adds_new_user(String userType) {

        String librarianToken = LibraryAPIUtilities.getToken(ConfigurationReader.getProperty("librarian_username"), ConfigurationReader.getProperty("librarian_password"));

        int userGroup = 1;

        if (userType.toLowerCase().equals("librarian")) {
            userGroup = 2;
        } else if (userType.toLowerCase().equals("student")) {
            userGroup = 3;
        } else {
            throw new RuntimeException("Wrong user group");
        }

        response = given().header("x-library-token", librarianToken)
                .and().accept(ContentType.JSON)
                .formParams(LibraryAPIUtilities.createUser(userGroup))
                .when().post("https://library1.cydeo.com/rest/v1/add_user")
                .prettyPeek();
    }

    @Then("user gets the response {string} as {string} and status code should be {int} after adding user")
    public void user_gets_the_response_as_and_status_code_should_be_after_adding_user(String message, String messageText, int statusCode) {
        Assert.assertEquals(statusCode, response.statusCode());
        Assert.assertEquals(messageText, response.path(message));
    }


}
