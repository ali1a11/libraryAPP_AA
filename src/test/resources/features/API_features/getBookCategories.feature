Feature: user gets book categories

  User story: As a user, I should be able get book categories

  @getBookCategories
  Scenario Outline: "<userType>" user gets book categories
    Given "<userType>" makes GET request to list book categories
    Then user gets the book categories list
      | Action and Adventure    |
      | Anthology               |
      | Classic                 |
      | Comic and Graphic Novel |
      | Crime and Detective     |
      | Drama                   |
      | Fable                   |
      | Fairy Tale              |
      | Fan-Fiction             |
      | Fantasy                 |
      | Historical Fiction      |
      | Horror                  |
      | Science Fiction         |
      | Biography/Autobiography |
      | Humor                   |
      | Romance                 |
      | Short Story             |
      | Essay                   |
      | Memoir                  |
      | Poetry                  |

    Examples:
      | userType  |
      | librarian |
      | student   |

