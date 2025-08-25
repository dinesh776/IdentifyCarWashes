Feature: Validate Filtered List Item Count

  Background:
    Given the user enters the home page
    And handles any popups
    And the user enters the location and a valid service
    And the user clicks on the search button
    Then the user is redirected to the service page

  Scenario: Verify that the number of displayed items matches the expected count
    When the user selects "4.5+" rating in filters
    Then the number of retrieved items should be equal to the expected count