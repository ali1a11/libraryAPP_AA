package com.cydeo.library.step_definitions.UIStepDefinitions;

import com.cydeo.library.pages.DashboardPage;
import com.cydeo.library.pages.LoginPage;
import com.cydeo.library.utilities.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.groovy.json.internal.Exceptions;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static io.restassured.RestAssured.*;

public class DashboardVerificationStepDefinitions {
    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();

    String usersCount_UI;
    String booksCount_UI;
    String borrowedBooksCount_UI;

    String usersCount_API;
    String booksCount_API;
    String borrowedBooksCount_API;

    String usersCount_DB;
    String booksCount_DB;
    String borrowedBooksCount_DB;

    @When("user logs in library app as librarian")
    public void user_logs_in_library_app_as_librarian() {
        loginPage.login(ConfigurationReader.getProperty("librarian_username"), ConfigurationReader.getProperty("librarian_password"));
    }

    @When("user gets dashboard information about users, books and borrowed books")
    public void user_gets_dashboard_information_about_users_books_and_borrowed_books() {

        BrowserUtils.waitFor(2);

        usersCount_UI = dashboardPage.usersCount.getText();
        booksCount_UI = dashboardPage.booksCount.getText();
        borrowedBooksCount_UI = dashboardPage.borrowedBooksCount.getText();
    }

    @When("user sends get request to dashboard_stats library API endpoint as librarian")
    public void user_sends_get_request_to_dashboard_stats_library_api_endpoint_as_librarian() {
        String librarianToken = APIUtilities.getToken(ConfigurationReader.getProperty("librarian_username"), ConfigurationReader.getProperty("librarian_password"));
        String librarianBearerToken = "Bearer " + librarianToken;

        Response response = given().accept(ContentType.JSON)
                .header("Authorization", librarianBearerToken)
                .header("x-library-token", librarianToken)
                .get("/dashboard_stats"); //"https://library1.cydeo.com/rest/v1/dashboard_stats"

        response.prettyPrint();

        usersCount_API = response.path("users");
        booksCount_API = response.path("book_count");
        borrowedBooksCount_API = response.path("borrowed_books");
    }

    @Then("dashboard information from UI and API should be match")
    public void dashboard_information_from_ui_and_api_should_be_match() {
        Assert.assertEquals(usersCount_API, usersCount_UI);
        Assert.assertEquals(booksCount_API, booksCount_UI);
        Assert.assertEquals(borrowedBooksCount_API, borrowedBooksCount_UI);
    }

    @When("user gets dashboard information from DB")
    public void user_gets_dashboard_information_from_db() {
        String query1 = "select count(*) from users";
        String query2 = "select count(*) from books";
        String query3 = "select count(*) from book_borrow where is_returned =0";

        Long usersCount_DBL = (Long) DBUtils.getQueryResultMap(query1).get(0).get("count(*)");
        usersCount_DB = usersCount_DBL.toString();

        Long booksCount_DBL = (Long) DBUtils.getQueryResultMap(query2).get(0).get("count(*)");
        booksCount_DB = booksCount_DBL.toString();

        Long borrowedBooksCount_DBL = (Long) DBUtils.getQueryResultMap(query3).get(0).get("count(*)");
        borrowedBooksCount_DB = borrowedBooksCount_DBL.toString();
    }

    @Then("dashboard information from UI, API, DB should be match")
    public void dashboard_information_from_ui_api_db_should_be_match() {

        Assert.assertEquals(usersCount_API, usersCount_UI);
        Assert.assertEquals(booksCount_API, booksCount_UI);
        Assert.assertEquals(borrowedBooksCount_API, borrowedBooksCount_UI);

        Assert.assertEquals(usersCount_API, usersCount_DB);
        Assert.assertEquals(booksCount_API, booksCount_DB);
        Assert.assertEquals(borrowedBooksCount_API, borrowedBooksCount_DB);


    }


}
