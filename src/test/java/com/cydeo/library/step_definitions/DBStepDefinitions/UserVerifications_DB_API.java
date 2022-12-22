package com.cydeo.library.step_definitions.DBStepDefinitions;

import com.cydeo.library.utilities.APIUtilities;
import com.cydeo.library.utilities.DBUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

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


}
