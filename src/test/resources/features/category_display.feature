@justdial @categories
Feature: Category Display

  @ui @categories
  Scenario: Check all categories are displayed
    Given the user enters the home page
    And handles any popups
    When the user clicks on the View All Categories button
    Then all 39 categories should appear in the popup
