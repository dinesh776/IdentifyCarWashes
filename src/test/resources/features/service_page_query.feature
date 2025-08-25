@regression
Feature: Service Page Query Submission

  Background:
    Given the user is on the home page
    And all popups are handled
    When the user enters a location "Near me" and a valid service "Car Washing Service"
    And the user clicks the search button
    Then the user should be redirected to the service page

  @sanity
  Scenario: Successful query on the service page
    When the user enters a valid name and valid phone number into the fields
    And the user clicks the service page submit button
    Then the OTP window should appear

  @regression
  Scenario: Unsuccessful query with invalid phone number
    When the user enters a valid name and an invalid phone number into the fields
    And the user clicks the service page submit button
    Then the error message "Please enter a valid mobile" should appear on the service page for invalid number

  @regression
  Scenario: Unsuccessful query with invalid name
    When the user enters an invalid name and a valid phone number into the fields
    And the user clicks the service page submit button
    Then the error message "Please enter a valid name" should appear on the service page for invalid name

  @regression
  Scenario: Unsuccessful query with invalid name and phone number
    When the user enters an invalid name and an invalid phone number into the fields
    And the user clicks the service page submit button
    Then the error message "Please enter a valid name" should appear on the service page for invalid name and number
