
Feature: Everybody can retrieve locations

  Scenario: I am not connected and I want to retrieve locations.
    Given I am not connected
    When I send a GET request to /locations/all
    Then I should get the first page of notifications and a 200 code.