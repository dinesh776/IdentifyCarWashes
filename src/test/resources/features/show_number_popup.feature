@regression
Feature: Show Number Popup

  Background:
    Given the user is on the home page
    And all popups are handled
    When the user enters a location "Near me" and a valid service "Car Washing Service"
    And the user clicks the search button
    Then the user should be redirected to the service page

  @regression
  Scenario: Verify phone number display in show number popup
    When all filters are selected accordingly
    And the user clicks the show number button
    Then the phone number should appear in the popup
