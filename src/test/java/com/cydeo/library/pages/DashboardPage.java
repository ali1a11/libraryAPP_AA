package com.cydeo.library.pages;

import com.cydeo.library.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

    public DashboardPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(xpath = "//*[@id='navbarDropdown']/span")
    public WebElement userFullName;

    @FindBy(xpath = "//*[.='Users']")
    public WebElement usersButton;

    @FindBy(xpath = "//*[.='Books']")
    public WebElement booksButton;

    @FindBy(id="user_count")
    public WebElement usersCount;

    @FindBy (id="book_count")
    public WebElement booksCount;

    @FindBy(id="borrowed_books")
    public WebElement borrowedBooksCount;




}
