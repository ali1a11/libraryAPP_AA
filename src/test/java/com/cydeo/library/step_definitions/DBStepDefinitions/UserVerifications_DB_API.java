package com.cydeo.library.step_definitions.DBStepDefinitions;

import com.cydeo.library.utilities.APIUtilities;
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

public class UserVerifications_DB_API {

    String token;
    Response response;
    String id_API = "";
    String fullName_API = "";
    String email_API = "";
    String userGroupID_API = "";


    @Given("user login API using {string} {string}")
    public void user_login_api_using(String username, String password) {
        token = APIUtilities.getToken(username, password);
        String bearerToken = "Bearer " + token;
                response = given().accept(ContentType.JSON)
                .and().contentType("application/x-www-form-urlencoded")
                .header("Authorization", bearerToken)
                .formParam("token", token)
                .when().post("https://library1.cydeo.com/rest/v1/decode");
        response.prettyPrint();
    }



    @When("I get the current user information from API")
    public void i_get_the_current_user_information_from_api() {
        id_API = response.path("id");
        fullName_API = response.path("full_name");
        email_API = response.path("email");
        userGroupID_API = response.path("user_group_id");
    }

    @Then("the current user information from API and database should match")
    public void the_current_user_information_from_api_and_database_should_match() {

        String query = "select id, full_name, email, user_group_id from users where email= '"+ email_API +"'";

        Map<String, Object> dbRowMap = DBUtils.getRowMap(query);

        System.out.println("dbRowMap = " + dbRowMap);

        Long id_DBL = (Long) dbRowMap.get("id");
        String id_DB = Long.toString(id_DBL);
        String fullName_DB = (String) dbRowMap.get("full_name");
        String email_DB = (String) dbRowMap.get("email");
        Integer userGroupID_DBi = (Integer) dbRowMap.get("user_group_id");
        String userGroupID_DB = Integer.toString(userGroupID_DBi);
        Assert.assertEquals(id_DB, id_API);
        Assert.assertEquals(fullName_DB, fullName_API);
        Assert.assertEquals(email_DB, email_API);
        Assert.assertEquals(userGroupID_DB, userGroupID_API);
    }


    @Test
    public void test1(){
        String studentUsername ="student1@library";
        String studentPassword ="i2A9TgXa";

        String librarianUsername ="librarian1@library";
        String librarianPassword ="rs4BNN9G";

        String studentToken = APIUtilities.getToken(studentUsername, studentPassword);

        String studentbearerToken = "Bearer " + studentToken;
        Response studentResponse  = given().accept(ContentType.JSON)
                .and().contentType("application/x-www-form-urlencoded")
                .header("Authorization", studentbearerToken)
                .formParam("token", studentToken)
                .when().post("https://library1.cydeo.com/rest/v1/decode");
        studentResponse.prettyPrint();

        String student_id_API = studentResponse.path("id");
        String student_fullName_API = studentResponse.path("full_name");
        String student_email_API = studentResponse.path("email");
        String student_userGroupID_API = studentResponse.path("user_group_id");

        String librarianToken = APIUtilities.getToken(librarianUsername, librarianPassword);

        String librarianbearerToken = "Bearer " + studentToken;
        Response librarianResponse  = given().accept(ContentType.JSON)
                .and().contentType("application/x-www-form-urlencoded")
                .header("Authorization", librarianbearerToken)
                .formParam("token", librarianToken)
                .when().post("https://library1.cydeo.com/rest/v1/decode");
        librarianResponse.prettyPrint();

        String librarian_id_API = librarianResponse.path("id");
        String librarian_fullName_API = librarianResponse.path("full_name");
        String librarian_email_API = librarianResponse.path("email");
        String librarian_userGroupID_API = librarianResponse.path("user_group_id");

        // DB
        DBUtils.createConnection();

        String query = "select id, full_name, email, user_group_id from users where email= '"+ studentUsername +"'";

        Map<String, Object> dbMapStudent = DBUtils.getRowMap(query);

        System.out.println("dbMapStudent = " + dbMapStudent);

        Long student_id_DBL = (Long) dbMapStudent.get("id");
        String student_id_DB = Long.toString(student_id_DBL);
        String student_fullName_DB = (String) dbMapStudent.get("full_name");
        String student_email_DB = (String) dbMapStudent.get("email");
        Integer student_userGroupID_DBi = (Integer) dbMapStudent.get("user_group_id");
        String student_userGroupID_DB = Integer.toString(student_userGroupID_DBi);

        Assert.assertEquals(student_id_DB, student_id_API);
        Assert.assertEquals(student_fullName_DB, student_fullName_API);
        Assert.assertEquals(student_email_DB, student_email_API);
        Assert.assertEquals(student_userGroupID_DB, student_userGroupID_API);

        DBUtils.destroyConnection();


        DBUtils.createConnection();

        String query2 = "select id, full_name, email, user_group_id from users where email= '"+ librarianUsername +"'";

        Map<String, Object> dbMaplibrarian = DBUtils.getRowMap(query2);

        System.out.println("dbMaplibrarian = " + dbMaplibrarian);

        DBUtils.destroyConnection();

        Long librarian_id_DBL = (Long) dbMaplibrarian.get("id");
        String librarian_id_DB = Long.toString(librarian_id_DBL);
        String librarian_fullName_DB = (String) dbMaplibrarian.get("full_name");
        String librarian_email_DB = (String) dbMaplibrarian.get("email");
        Integer librarian_userGroupID_DBi = (Integer) dbMaplibrarian.get("user_group_id");
        String librarian_userGroupID_DB = Integer.toString(librarian_userGroupID_DBi);

        Assert.assertEquals(librarian_id_DB, librarian_id_API);
        Assert.assertEquals(librarian_fullName_DB, librarian_fullName_API);
        Assert.assertEquals(librarian_email_DB, librarian_email_API);
        Assert.assertEquals(librarian_userGroupID_DB, librarian_userGroupID_API);
    }


}
