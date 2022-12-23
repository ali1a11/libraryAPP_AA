Feature: Login in API

  @login-api
  Scenario Outline: User can login with valid credentials <username> <password>
    Given get token with "<username>" and "<password>"
    Then status code should be 200

    Examples:
      | username           | password |
      | librarian1@library | rs4BNN9G |
      | librarian2@library | eb2VQKEj |
      | student6@library   | P2OEdBiW |


  Scenario Outline: User cannot get token with invalid credentials <username> <password>
    Given get token with "<username>" and "<password>"
    Then response body returns error "Sorry, Wrong Email or Password" and status code should be 404

    Examples: wrong credentials
      | username           | password  |
      | student1@library   | WrongPass |
      | WrongUser@library  | 80qynl9d  |
      | student3@library   |           |
      |                    | 1AHF6MHk  |
      | student5@library   | uElqih    |
      | librarian1@library | WrongPass |
      | WrongUser@library  | eb2VQKEj  |
      | librarian3@library |           |
      |                    | 7jrGZdaV  |
      | WrongUser          | 4p4ewVgW  |
      |                    |           |