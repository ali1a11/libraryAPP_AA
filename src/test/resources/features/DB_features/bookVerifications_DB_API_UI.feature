Feature: Book verification DB API UI

  @bookVerification @db
  Scenario Outline: Verify book information from database, API and UI

    Given user is on the library login page
    And user enters username "<username>" password "<password>" and logins
    And user gets the book "Mokuta" information from UI
    And user "<username>" "<password>" gets the book information with id 1 from API
    And user gets the book information from DB
    Then UI, API and Database user information must be match with the given book information
      | id               | 1                                                                                      |
      | name             | Mokuta                                                                                 |
      | isbn             | 387254926519                                                                           |
      | year             | 2021                                                                                   |
      | author           | Queen                                                                                  |
      | book_category_id | 3                                                                                      |
      | description      | Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris. |

    Examples:
      | username           | password |
      | librarian1@library | rs4BNN9G |



