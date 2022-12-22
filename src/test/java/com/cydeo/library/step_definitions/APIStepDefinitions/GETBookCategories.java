package com.cydeo.library.step_definitions.APIStepDefinitions;

import com.cydeo.library.utilities.APIUtilities;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;

import static io.restassured.RestAssured.*;

public class GETBookCategories {

    Response response;

    @Given("{string} makes GET request to list book categories")
    public void makes_get_request_to_list_book_categories(String userType) {

        String token = "";

        if (userType.toLowerCase().equals("librarian")) {
            token = APIUtilities.getLibrarianToken();
        } else if (userType.toLowerCase().equals("student")) {
            token = APIUtilities.getStudentToken();
        }

        response = given().accept(ContentType.JSON)
                .and().header("x-library-token", token)
                .when()
                .get("https://library1.cydeo.com/rest/v1/get_book_categories");

    }

    @Then("user gets the book categories list")
    public void user_gets_the_book_categories_list(List<String> bookCategories) {

        Assert.assertEquals(bookCategories, response.path("name"));

    }

}
