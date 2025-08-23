@justdial @freelisting
Feature: Free Listing Login

  @positive @freelisting
  Scenario: Free listing login with valid phone number
    Given the user enters the Free Listing page
    When the user enters a valid phone number into the field
    And the user clicks on the submit button
    Then the OTP popup will appear

  @negative @freelisting
  Scenario: Free listing login with invalid phone number
    Given the user enters the Free Listing page
    When the user enters an invalid phone number into the field
    And the user clicks on the submit button
    Then the error message "Please Enter a Valid Mobile Number" will appear in free listing page

  Scenario: Free listing login with empty phone number
    Given the user enters the Free Listing page
    When the user clicks on the submit button
    Then the error message "Please Enter a Mobile Number" will appear in free listing page for empty field
