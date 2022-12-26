package com.cydeo.library.utilities;

import com.cydeo.library.pages.BooksPage;

import java.util.HashMap;
import java.util.Map;

public class BookUtilities {
    public static Map<String, String> getBookInformation(String bookName){

        Map<String, String> bookInfo = new HashMap<>();

        BooksPage booksPage = new BooksPage();

        booksPage.searchBox.sendKeys(bookName);

        booksPage.editBookButton(bookName).click();
        BrowserUtils.waitFor(3);

        bookInfo.put("bookName_UI", booksPage.bookNameInputbox.getAttribute("value"));
        bookInfo.put("ISBN_UI", booksPage.ISBNInputbox.getAttribute("value"));
        bookInfo.put("year_UI", booksPage.yearInputbox.getAttribute("value"));
        bookInfo.put("author_UI", booksPage.authorInputbox.getAttribute("value"));
        bookInfo.put("description_UI", booksPage.descriptionInputbox.getAttribute("value"));

        return bookInfo;

    }
}
