package com.cydeo.library.step_definitions.UIStepDefinitions;

import com.cydeo.library.pages.BooksPage;
import com.cydeo.library.pages.DashboardPage;
import com.cydeo.library.pages.LoginPage;
import com.cydeo.library.utilities.BrowserUtils;
import com.cydeo.library.utilities.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class AddBook_StepDefinitions {

    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();
    BooksPage booksPage = new BooksPage();


    @Given("user logins to application as a {string}")
    public void user_logins_to_application_as_a(String userCategory) throws Exception {

        String username = "";
        String password = "";

        switch (userCategory.toLowerCase()) {

            case "librarian":
                username = ConfigurationReader.getProperty("librarian_username");
                password = ConfigurationReader.getProperty("librarian_password");
                break;

            case "student":
                username = ConfigurationReader.getProperty("student_username");
                password = ConfigurationReader.getProperty("student_password");
                break;

            default:
                throw new Exception("Wrong user type is provided: " + userCategory);
        }

        loginPage.login(username, password);

    }


    @Given("user navigates to {string} page")
    public void user_navigates_to_page(String page) {

        switch (page.toLowerCase()) {
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
        booksPage.addBookButton.click();
    }


    String bookName = "";
    String ISBN = "";
    String year = "";
    String author = "";
    String description = "";

    @Given("user enters book information and clicks on Save changes button")
    public void user_enters_book_information_and_clicks_on_save_changes_button(Map<String, Object> bookInfo) {

        bookName = bookInfo.get("Book_name").toString();
        booksPage.bookNameInputbox.sendKeys(bookName);
        ISBN = bookInfo.get("ISBN").toString();
        booksPage.ISBNInputbox.sendKeys(ISBN);
        year = bookInfo.get("Year").toString();
        booksPage.yearInputbox.sendKeys(year);
        author = bookInfo.get("Author").toString();
        booksPage.authorInputbox.sendKeys(author);
        booksPage.selectBookCategory().selectByVisibleText(bookInfo.get("Book_category").toString());
        description = bookInfo.get("Description").toString();
        booksPage.descriptionInputbox.sendKeys(description);
        booksPage.saveChangesButton.click();

        BrowserUtils.waitFor(3);

    }

    @Then("the book is on the list")
    public void the_book_is_on_the_list() {
        booksPage.searchBox.sendKeys(bookName);

        booksPage.editBookButton(bookName).click();
        BrowserUtils.waitFor(3);

        String actualBookName = booksPage.bookNameInputbox.getAttribute("value");
        String actualISBN = booksPage.ISBNInputbox.getAttribute("value");
        String actualYear = booksPage.yearInputbox.getAttribute("value");
        String actualAuthor = booksPage.authorInputbox.getAttribute("value");
        String actualDescription = booksPage.descriptionInputbox.getAttribute("value");

        Assert.assertEquals(bookName,actualBookName);
        Assert.assertEquals(ISBN, actualISBN);
        Assert.assertEquals(year, actualYear);
        Assert.assertEquals(author, actualAuthor);
        Assert.assertEquals(description, actualDescription);
    }






}
