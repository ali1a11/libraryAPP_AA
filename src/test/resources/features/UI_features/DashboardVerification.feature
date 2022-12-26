
  Feature: Dashboard verification

    @dashboard
    Scenario: Dashboard information verification
      Given user is on the library login page
      When user logs in library app as librarian
      And user gets dashboard information about users, books and borrowed books
      And user sends get request to dashboard_stats library API endpoint as librarian
      Then dashboard information from UI and API should be match
