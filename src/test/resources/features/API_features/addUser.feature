Feature: librarian adds user on api

  User story: As a librarian user, I should be able to add new user on API

  Scenario Outline: librarian adds user on api <userType>
    Given librarian adds new "<userType>" user
    Then user gets the response "message" as "The user has been created." and status code should be 200 after that
    Examples:
      | userType  |
      | librarian |
      | student   |


  @addUserAPI
  Scenario Outline: student adds user on api <userType>
    Given student adds new "<userType>" user
    Then user gets the response "error" as "Unauthorized Access" and status code should be 403 after that
    Examples:
      | userType  |
      | librarian |
      | student   |
