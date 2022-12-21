Feature: librarian adds user on api

  User story: As a librarian user, I should be able to add new user on API

  @addUserAPI
  Scenario Outline: librarian adds user on api <userType>
    Given librarian adds new "<userType>" user
      | full_name     | Jack Markman    |
      | email         | emailJM@library |
      | password      | password64      |
      | user_group_id | 3               |
      | status        | ACTIVE          |
      | start_date    | 2023-01-01      |
      | end_date      | 2023-12-31      |
      | address       | Test address    |

    Then user gets the response "message" as "The user has been created." and status code should be 200
    Examples:
      | userType  |
      | librarian |
      | student   |
