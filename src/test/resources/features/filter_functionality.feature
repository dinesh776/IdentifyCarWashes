@regression
Feature: Filter Functionality

  Background:
    Given the user is on the home page
    And all popups are handled
    When the user enters a location "Near me" and a valid service "Car Washing Service"
    And the user clicks the search button
    Then the user should be redirected to the service page

  @regression
  Scenario: Verify filter application for ratings
    When the user selects "4.5+" rating in filters
    Then all result items should have a rating of 4.5 or higher
