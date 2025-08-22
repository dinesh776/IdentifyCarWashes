@justdial @query
Feature: Service Page Query Submission

  Background:
    Given the user enters the home page
    And handles any popups
    And the user enters the location and a valid service
    And the user clicks on the search button
    Then the user is redirected to the service page

  @positive @query
  Scenario: Successful query on the service page
    When the user enters a valid name and valid phone number into the fields
    And the user clicks on the service page submit button
    Then the OTP window will appear

  @negative @query
  Scenario: Unsuccessful query with invalid phone number
    When the user enters a valid name and an invalid phone number into the fields
    And the user clicks on the service page submit button
    Then the error message "Please enter a valid mobile" will appear in service page for invalid number

  @negative @query
  Scenario: Unsuccessful query with invalid name
    When the user enters an invalid name and a valid phone number into the fields
    And the user clicks on the service page submit button
    Then the error message "Please enter a valid name" will appear in service page for invalid name

  @negative @query
  Scenario: Unsuccessful query with invalid name and phone number
    When the user enters an invalid name and an invalid phone number into the fields
    And the user clicks on the service page submit button
    Then the error message "Please enter a valid name" will appear in service page for invalid name and number
