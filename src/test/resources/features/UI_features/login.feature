
  Feature: Library app login feature

    User Story: As a user, I should be able to login with correct credentials to librarian and student accounts.

    @login
    Scenario Outline: login
      Given user is on the library login page
      When user enters username "<username>" password "<password>" and logins
      Then user should see "<expectedname>"

      @students
      Examples: students
        | username         | password | expectedname   |
        | student1@library | i2A9TgXa | Test Student 1 |
        | student2@library | 80qynl9d | Test Student 2 |
        | student3@library | 1f3ZGRGj | Test Student 3 |
        | student4@library | 1AHF6MHk | Test Student 4 |
        | student5@library | uElqihO2 | Test Student 5 |

      @librarians
      Examples: librarians
        | username           | password | expectedname     |
        | librarian1@library | rs4BNN9G | Test Librarian 1 |
        | librarian2@library | eb2VQKEj | Test Librarian 2 |
        | librarian3@library | I6JMMwLb | Test Librarian 3 |
        | librarian4@library | 7jrGZdaV | Test Librarian 4 |
        | librarian5@library | 4p4ewVgW | Test Librarian 5 |



    Scenario Outline: Not login with invalid credentials <username> <password>
      Given user is on the library login page
      When user enters username "<username>" password "<password>" and logins
      Then user should not be able to login on library app

      Examples: wrong credentials
        | username           | password  |
        | student1@library   | WrongPass |
        | WrongUser@library  | 80qynl9d  |
        | student3@library   |           |
        |                    | 1AHF6MHk  |
        | student5@library   | uElqihO2  |
        | librarian1@library | WrongPass |
        | WrongUser@library  | eb2VQKEj  |
        | librarian3@library |           |
        |                    | 7jrGZdaV  |
        | WrongUser          | 4p4ewVgW  |
        |                    |           |


