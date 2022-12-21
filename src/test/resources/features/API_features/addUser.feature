Feature: librarian adds user on api

  User story: As a librarian user, I should be able to add new user on API

  @addUserAPI
  Scenario Outline: librarian adds user on api <userType>
    Given librarian adds new "<userType>" user

    Then user gets the response "message" as "The user has been created." and status code should be 200 after adding user
    Examples:
      | userType  |
      | librarian |
      | student   |
