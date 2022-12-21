Feature: add book on API
  User Story: As a librarian user, I should be able to add new book on API

  @addBookAPI
  Scenario: Add book as a librarian on API

    Given librarian makes POST request to add book
      | name             | White eye              |
      | isbn             | 9955336677             |
      | year             | 2021                   |
      | author           | Lerry Berry            |
      | book_category_id | 7                      |
      | description      | Lorem ipsum dolor sit. |
    Then user gets the response "message" as "The book has been created." and status code should be 200


  Scenario: Add book as a student on API

    Given student makes POST request to add book
      | name             | White eye              |
      | isbn             | 9955336677             |
      | year             | 2021                   |
      | author           | Lerry Berry            |
      | book_category_id | 7                      |
      | description      | Lorem ipsum dolor sit. |

    Then user gets the response "error" as "Unauthorized Access" and status code should be 403