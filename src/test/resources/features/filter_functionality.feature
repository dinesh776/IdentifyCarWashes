@justdial @filters
Feature: Filter Functionality

  Background:
    Given the user enters the home page
    And handles any popups
    And the user enters the location and a valid service
    And the user clicks on the search button
    Then the user is redirected to the service page
    
  @ui @filters
  Scenario: Check whether the filters are applied properly
    When the user selects "4.5+" rating in filters
    Then the result items rating should be greater than or equal to the 4.5 rating
