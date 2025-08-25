@sanity
Feature: Search Functionality

  Background:
    Given the user is on the home page
    And all popups are handled

  @sanity
  Scenario: Successful search
    When the user enters a location "Near me" and a valid service "Car Washing Service"
    And the user clicks the search button
    Then the user should be redirected to the service page


  @regression
  Scenario: Unsuccessful search
    When the user enters a location and leaves the service field empty
    And the user clicks the search button with invalid data
    Then the user should remain on the home page
