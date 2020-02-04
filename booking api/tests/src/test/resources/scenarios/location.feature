
Feature: Everybody can retrieve locations but only admins can change them

  Scenario: I don't have a valid user token and I can retrieve locations.
    Given I have no valid token
    When I send a GET request to /location/all
    Then I should get the first page of locations
    And The status code is 200

  Scenario: I have a valid user token and I can retrieve locations.
    Given I am connected as a user
    When I send a GET request to /location/all
    Then I should get the first page of locations
    And The status code is 200


  Scenario: I have no valid token so I cannot add locations
    Given I have no valid token
    When I send a POST request to /location with a new location
    Then The status code is 401

  Scenario: I am just a user so I cannot add locations
    Given I am connected as a user
    When I send a POST request to /location with a new location
    Then The status code is 403

  Scenario: I have no valid token so I cannot edit a valid location
    Given I have no valid token
    When I send a PUT request to /location with an edited location for id = 3
    Then The status code is 401

  Scenario: I am just a user so I cannot edit a valid location
    Given I am connected as a user
    When I send a PUT request to /location with an edited location for id = 3
    Then The status code is 403

  Scenario: I have no valid token so I cannot delete a valid location
    Given I have no valid token
    When I send a DELETE request to /location for id = 3
    Then The status code is 401

  Scenario: I am just a user so I cannot delete a valid location
    Given I am connected as a user
    When I send a DELETE request to /location for id = 3
    Then The status code is 403




  Scenario: I am an admin then I can add locations
    Given I am connected as an admin
    When I send a POST request to /location with a new location
    Then The status code is 204

  Scenario: I am an admin but I cannot insert a malformed location
    Given I am connected as an admin
    When I send a POST request to /location with a malformed location
    Then The status code is 400

  Scenario: I am an admin then I can edit a valid location
    Given I am connected as an admin
    When I send a PUT request to /location with an edited location for id = 3
    Then The status code is 204

  Scenario: I am an admin but I cannot edit an invalid location
    Given I am connected as an admin
    When I send a PUT request to /location with an edited location for id = 352
    Then The status code is 404

  Scenario: I am an admin then I can delete a valid location
    Given I am connected as an admin
    When I send a DELETE request to /location for id = 3
    Then The status code is 204

  Scenario: I am an admin but I cannot delete an invalid location
    Given I am connected as an admin
    When I send a DELETE request to /location for id = 314
    Then The status code is 404



