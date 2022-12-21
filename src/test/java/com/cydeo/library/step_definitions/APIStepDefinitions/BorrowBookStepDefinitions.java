package com.cydeo.library.step_definitions.APIStepDefinitions;

import com.cydeo.library.utilities.LibraryAPIUtilities;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.*;

public class BorrowBookStepDefinitions {

Response response;

    @Given("the user as a student makes POST request to borrow a book with user_id {int}")
    public void the_user_as_a_student_makes_post_request_to_borrow_a_book_with_user_id(int userID) {


        int bookIDtoBorrow = LibraryAPIUtilities.getBorrowableBook();

         response = given().header("x-library-token", LibraryAPIUtilities.getStudentToken())
                .and().accept(ContentType.JSON)
                .contentType("application/x-www-form-urlencoded")
                .formParam("book_id", bookIDtoBorrow)
                .formParam("user_id", userID)
                .when().post("https://library1.cydeo.com/rest/v1/book_borrow");


    }
    @Then("user gets the response {string} as {string} with status code should be {int}")
    public void user_gets_the_response_as_with_status_code_should_be(String message, String messageText, int expectedStatusCode) {

        Assert.assertEquals(messageText, response.path(message));
        Assert.assertEquals(expectedStatusCode, response.statusCode());

    }


}
