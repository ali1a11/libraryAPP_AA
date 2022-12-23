Feature: User verification DB-API

  @WIP_DB-API @db
  Scenario Outline: Verify user information from API and database
    Given user login API using "<username>" "<password>"
    When I get the current user information from API
    Then the current user information from API and database should match

    Examples:
      | username         | password |
      | student1@library | i2A9TgXa |
      | student2@library | 80qynl9d |
      | library1@library | rs4BNN9G |


