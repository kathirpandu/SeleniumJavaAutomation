@Bank
Feature: Framework banking Login

  Scenario: Open HDFC Net Banking
    When user Login HDFC NetBanking
    Then Entering Credentials
    And Validating Login
