@smoke
Feature: Framework Smoke Test

  Scenario: Open MakeMyTrip and navigate Flights
    When user selects flights option
    Then framework should execute successfully
