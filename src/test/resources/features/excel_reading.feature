Feature:Data driven testing
@sanity
  @SaveJson
Scenario Outline: Testing core functionality using Excel-based input data
  Given the user is on the home page
  And all popups are handled
  When the user enters a location and a valid service with required filters with index <row>
  Then user redirected to the specified service page
  And Retrieve services
  And Save services to json file

  Examples:
  |row|
  |1  |
  |2  |
#  |3  |
#  |4  |
#  |5  |
#  |6  |
#  |7  |
#  |8  |
#  |9  |