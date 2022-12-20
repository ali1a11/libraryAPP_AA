package com.cydeo.library.step_definitions;

import com.cydeo.library.pages.Books;
import com.cydeo.library.pages.DashboardPage;
import com.cydeo.library.pages.LoginPage;
import com.cydeo.library.utilities.ConfigurationReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.Map;

public class AddBook_StepDefinitions {

    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();
    Books books = new Books();


    @Given("user logins to application as a {string}")
    public void user_logins_to_application_as_a(String userCategory) throws Exception {

        String username = "";
        String password = "";

        switch (userCategory.toLowerCase()) {

            case "librarian":
                username= ConfigurationReader.getProperty("librarian_username");
                password= ConfigurationReader.getProperty("librarian_password");
                break;

            case "student":
                username= ConfigurationReader.getProperty("student_username");
                password= ConfigurationReader.getProperty("student_password");
                break;

            default:
                throw new Exception("Wrong user type is provided: " + userCategory);
        }

        loginPage.login(username, password);

    }


    @Given("user navigates to {string} page")
    public void user_navigates_to_page(String page) {

        switch (page.toLowerCase()){
            case "books":
                dashboardPage.booksButton.click();
                break;
            case "users":
                dashboardPage.usersButton.click();
                break;
        }


    }

    @Given("user clicks on AddBook button")
    public void user_clicks_on_add_book_button() {
        books.addBookButton.click();
    }

    @Given("user enters book information and clicks on Save changes button")
    public void user_enters_book_information_and_clicks_on_save_changes_button(Map<String, Object> bookInfo) {

        books.bookNameInputbox.sendKeys(bookInfo.get("Book_name").toString());
        books.ISBNInputbox.sendKeys(bookInfo.get("ISBN").toString());
        books.yearInputbox.sendKeys(bookInfo.get("Year").toString());
        books.authorInputbox.sendKeys(bookInfo.get("Author").toString());
        books.selectBookCategory().selectByVisibleText(bookInfo.get("Book_category").toString());
        books.descriptionInputbox.sendKeys(bookInfo.get("Description").toString());
        books.saveChangesButton.click();

    }

    @Then("the book is on the list")
    public void the_book_is_on_the_list() {


    }


}
