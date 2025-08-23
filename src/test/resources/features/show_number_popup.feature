@justdial @popup
Feature: Show Number Popup

 Background:
	Given the user enters the home page
	And handles any popups
	And the user enters the location and a valid service
	And the user clicks on the search button
	Then the user is redirected to the service page
  @ui @popup
  Scenario: Check information from the show number popup
    When all filters are selected accordingly
    And the user clicks on the show number button
    Then the phone number will appear in the popup