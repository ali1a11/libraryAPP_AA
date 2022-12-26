package com.cydeo.library.step_definitions.DBStepDefinitions;

import com.cydeo.library.pages.BooksPage;
import com.cydeo.library.pages.DashboardPage;
import com.cydeo.library.utilities.APIUtilities;
import com.cydeo.library.utilities.BrowserUtils;
import com.cydeo.library.utilities.DBUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class BookVerifications {

    DashboardPage dashboardPage = new DashboardPage();
    BooksPage booksPage = new BooksPage();


    String bookName_UI = "";
    String ISBN_UI = "";
    String year_UI = "";
    String author_UI = "";
    String description_UI = "";

    String bookID_API = "";
    String bookName_API = "";
    String ISBN_API = "";
    String year_API = "";
    String author_API = "";
    String book_category_id_API = "";
    String description_API = "";

    String bookID_DB = "";
    String bookName_DB = "";
    String ISBN_DB = "";
    String year_DB = "";
    String author_DB = "";
    String book_category_id_DB = "";
    String description_DB = "";

    @Given("user gets the book {string} information from UI")
    public void user_gets_the_book_information_from_ui(String bookName) {

        dashboardPage.booksButton.click();

        booksPage.searchBox.sendKeys(bookName);

        booksPage.editBookButton("Mokuta").click();
        BrowserUtils.waitFor(1);

        bookName_UI = booksPage.bookNameInputbox.getAttribute("value");
        ISBN_UI = booksPage.ISBNInputbox.getAttribute("value");
        year_UI = booksPage.yearInputbox.getAttribute("value");
        author_UI = booksPage.authorInputbox.getAttribute("value");
        description_UI = booksPage.descriptionInputbox.getAttribute("value");

        System.out.println("bookName_UI = " + bookName_UI);
    }


    @Given("user {string} {string} gets the book information with id {int} from API")
    public void user_gets_the_book_information_with_id_from_api(String username, String password, int bookID) {

        String bearertoken = "Bearer " + APIUtilities.getToken(username, password);
        String token = APIUtilities.getToken(username, password);

        Response response = given().accept("application/json")
                .contentType(ContentType.JSON)
                .pathParam("id", bookID)
                .header("Authorization", bearertoken)
                .header("x-library-token", token)
                .when().get("http://library1.cydeo.com/rest/v1/get_book_by_id/{id}");
        response.prettyPrint();

        bookID_API = response.path("id");
        bookName_API = response.path("name");
        ISBN_API = response.path("isbn");
        year_API = response.path("year");
        author_API = response.path("author");
        book_category_id_API= response.path("book_category_id");
        description_API = response.path("description");

        System.out.println("bookName_API = " + bookName_API);

    }


    @When("user gets the book information from DB")
    public void user_gets_the_book_information_from_db() {

        String query ="select id, name, isbn, year, author, book_category_id, description from books\n" +
                "where id = 1";
        
        Map<String, Object> dbrowMap = DBUtils.getRowMap(query);

        System.out.println("dbrowMap = " + dbrowMap);

        Long bookID_DBL =(Long) dbrowMap.get("id");

        bookID_DB = bookID_DBL.toString();
        bookName_DB = (String) dbrowMap.get("name");
        ISBN_DB = (String) dbrowMap.get("isbn");
        year_DB = (String) dbrowMap.get("year");
        author_DB = (String)dbrowMap.get("author");
        Integer book_category_id_DBInteger =(Integer) dbrowMap.get("book_category_id");
        book_category_id_DB = book_category_id_DBInteger.toString();
        description_DB = (String) dbrowMap.get("description");


    }

    @Then("UI, API and Database user information must be match with the given book information")
    public void ui_api_and_database_user_information_must_be_match_with_the_given_book_information(Map<String, Object> bookInfo) {

        Assert.assertEquals(bookInfo.get("id"), bookID_DB);
        Assert.assertEquals(bookInfo.get("name"), bookName_DB);
        Assert.assertEquals(bookInfo.get("isbn"), ISBN_DB);
        Assert.assertEquals(bookInfo.get("year"), year_DB);
        Assert.assertEquals(bookInfo.get("author"), author_DB);
        Assert.assertEquals(bookInfo.get("book_category_id"), book_category_id_DB);
        Assert.assertEquals(bookInfo.get("description"), description_DB);


        Assert.assertEquals(bookInfo.get("id"), bookID_API);
        Assert.assertEquals(bookInfo.get("name"), bookName_API);
        Assert.assertEquals(bookInfo.get("isbn"), ISBN_API);
        Assert.assertEquals(bookInfo.get("year"), year_API);
        Assert.assertEquals(bookInfo.get("author"), author_API);
        Assert.assertEquals(bookInfo.get("book_category_id"), book_category_id_API);
        Assert.assertEquals(bookInfo.get("description"), description_API);


        Assert.assertEquals(bookInfo.get("name"), bookName_UI);
        Assert.assertEquals(bookInfo.get("isbn"), ISBN_UI);
        Assert.assertEquals(bookInfo.get("year"), year_UI);
        Assert.assertEquals(bookInfo.get("author"), author_UI);
        Assert.assertEquals(bookInfo.get("description"), description_UI);



    }

    @Test
    public void test(){

        DBUtils.createConnection();

        String query ="select id, name, isbn, year, author, book_category_id, description from books\n" +
                "where id = 1";

        Map<String, Object> dbMap = DBUtils.getRowMap(query);

        System.out.println("dbMap = " + dbMap);



        DBUtils.destroyConnection();


    }


}
