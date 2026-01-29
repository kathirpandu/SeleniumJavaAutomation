@regression
Feature: Flight Search - Lowest price current month (Round trip)

  Scenario: Book lowest price flight for current month with return
    Given user is on MakeMyTrip home page
    When user selects flights option
    And user selects round trip
    And user selects from city "Chennai"
    And user selects to city "Delhi"
    And user selects lowest price departure date in current month
    And user selects lowest price return date in current month
    And user clicks search flights
    Then user should see flight results page
