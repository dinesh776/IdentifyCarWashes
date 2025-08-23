@justdial @search
Feature: Search Functionality

  @positive @search
  Scenario: Successful search
    Given the user enters the home page
    And handles any popups
    When the user enters the location and a valid service
    And the user clicks on the search button
    Then the user is redirected to the service page

  @negative @search
  Scenario: Unsuccessful search
    Given the user enters the home page
    And handles any popups
    When the user enters the location and leaves the service field empty
    And the user clicks on the search button with invalid data
    Then the user stays in the home page
