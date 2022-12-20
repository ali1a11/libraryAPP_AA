package com.cydeo.library.step_definitions.UIStepDefinitions;

import com.cydeo.library.pages.DashboardPage;
import com.cydeo.library.pages.LoginPage;
import com.cydeo.library.utilities.ConfigurationReader;
import com.cydeo.library.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login_StepDefinitions {

    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();


    @Given("user is on the library login page")
    public void user_is_on_the_library_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url1"));
    }
    @When("user enters username {string} password {string} and logins")
    public void user_enters_username_password_and_logins(String username, String password) {
        loginPage.login(username, password);

    }
    @Then("user should see {string}")
    public void user_should_see(String expectedUserName) {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOf(dashboardPage.userFullName));

        String actualUserName = dashboardPage.userFullName.getText();

        System.out.println("actualUserName = " + actualUserName);

        Assert.assertEquals(expectedUserName,actualUserName);

    }

    @Then("user should not be able to login on library app")
    public void user_should_not_be_able_to_login_on_library_app() {

        Assert.assertEquals("http://library1.cydeo.com/login.html", Driver.getDriver().getCurrentUrl());

    }

}
