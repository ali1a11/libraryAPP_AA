Feature: librarian adds book
  User story= As a librarian I should be able to add new books
  @WIP
  Scenario: Librarian adds new book
    Given user is on the library login page
    And user logins to application as a "librarian"
    And user navigates to "Books" page
    And user clicks on AddBook button
    And user enters book information and clicks on Save changes button
      | Book_name     | Black eye2                  |
      | ISBN          | 1122558899                  |
      | Year          | 2019                        |
      | Author        | Henry Kong                  |
      | Book_category | Drama                       |
      | Description   | Lorem ipsum dolor sit amet. |
    Then the book is on the list