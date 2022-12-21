package com.cydeo.library.step_definitions.APIStepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.List;

public class GETBookCategories {

    @Given("{string} makes GET request to list book categories")
    public void makes_get_request_to_list_book_categories(String userType) {

        
    }
    @Then("user gets the book categories list")
    public void user_gets_the_book_categories_list(List<String>booCategories) {

    }

}
