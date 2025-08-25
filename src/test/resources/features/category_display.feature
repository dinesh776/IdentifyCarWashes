@sanity
Feature: Category Display

  @sanity
  Scenario: Verify all categories are visible
    Given the user is on the home page
    And all popups are handled
    When the user clicks View All Categories
    Then a popup should display all 39 categories
