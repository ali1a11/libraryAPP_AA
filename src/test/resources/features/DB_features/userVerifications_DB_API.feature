Feature: User verification DB API UI

  @db
  Scenario Outline: Verify user information from API and database
    Given user login API using "<username>" "<password>"
    When user gets the user information from API
    And user gets the user information from DB
    Then the current user information from API and database should match

    Examples:
      | username           | password |
      | student1@library   | i2A9TgXa |
      | student2@library   | 80qynl9d |
      | librarian1@library | rs4BNN9G |

  @WIP_DB-API-UI @db
  Scenario Outline: three point verification (UI, API, Database) DDT
    Given user is on the library login page
    And user enters username "<username>" password "<password>" and logins
    And user gets the user information from UI
    And user login API using "<username>" "<password>"
    And user gets the user information from API
    And user gets the user information from DB
    Then UI, API and Database user information must be match with "<expectedname>"

    Examples:
      | username           | password | expectedname     |
      | student1@library   | i2A9TgXa | Test Student 1   |
      | librarian1@library | rs4BNN9G | Test Librarian 1 |


