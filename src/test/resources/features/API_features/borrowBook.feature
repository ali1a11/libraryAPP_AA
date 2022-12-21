Feature: borrow book on api

  @borrowBook
  Scenario: Borrow a book as a student on API
    Given the user as a student makes POST request to borrow a book with user_id 10050

    Then user gets the response "message" as "The book has been borrowed..." with status code should be 200

