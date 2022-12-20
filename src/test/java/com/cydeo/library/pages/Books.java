package com.cydeo.library.pages;

import com.cydeo.library.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Books {

    public Books(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(css = "a[href='tpl/add-book.html']")
    public WebElement addBookButton;

    @FindBy(name = "name")
    public WebElement bookNameInputbox;

    @FindBy(name = "isbn")
    public WebElement ISBNInputbox;

    @FindBy(name = "year")
    public WebElement yearInputbox;

    @FindBy(name = "author")
    public WebElement authorInputbox;

    @FindBy(id = "description")
    public WebElement descriptionInputbox;

    @FindBy(id="book_group_id")
    public WebElement bookCategorySelect;

    public Select selectBookCategory (){
        return new Select(bookCategorySelect);
    }

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveChangesButton;




}
