Feature: librarian adds book
  User story= As a librarian I should be able to add new books
  @WIP
  Scenario: Librarian adds new book
    Given user is on the library login page
    And user logins to application as a "librarian"
    And user navigates to "Books" page
    And user clicks on AddBook button
    And user enters book information and clicks on Save changes button
      | Book_name     | Peace bird                  |
      | ISBN          | 23459865-2                  |
      | Year          | 2015                        |
      | Author        | Lorry Young                 |
      | Book_category | Poetry                      |
      | Description   | Lorem ipsum dolor sit amet. |
    Then the book is on the list