@sanity
Feature: Free Listing Login

  @sanity
  Scenario: Free listing login with valid phone number
    Given the user is on the Free Listing page
    When the user enters a valid phone number into the field
    And the user clicks the submit button
    Then the OTP popup should appear

  @regression
  Scenario: Free listing login with invalid phone number
    Given the user is on the Free Listing page
    When the user enters an invalid phone number into the field
    And the user clicks the submit button
    Then the error message "Please Enter a Valid Mobile Number" should appear on the Free Listing page

  @regression
  Scenario: Free listing login with empty phone number
    Given the user is on the Free Listing page
    When the user clicks the submit button
    Then the error message "Please Enter a Mobile Number" should appear on the Free Listing page for an empty field
