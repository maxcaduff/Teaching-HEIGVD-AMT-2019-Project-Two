
Feature: Everybody can retrieve activities but only admins can change them


  Scenario: I don't have a valid user token and I can retrieve activities.
    Given I have no valid token
    When I send a GET request to /activity/all
    Then I should get the first page of activities
    And The status code is 200

  Scenario: I have a valid user token and I can also retrieve activities.
    Given I am connected as a user
    When I send a GET request to /activity/all
    Then I should get the first page of activities
    And The status code is 200

  Scenario: I don't have a valid user token and I can retrieve availability of activities.
    Given I have no valid token
    When I send a GET request to /activity/availability/20211202 for page 1
    Then I should get the last page of activities results
    And The status code is 200


  Scenario: I have no valid token so I cannot add activities
    Given I have no valid token
    When I send a POST request to /activity with a new activity
    Then The status code is 401

  Scenario: I am just a user so I cannot add activities
    Given I am connected as a user
    When I send a POST request to /activity with a new activity
    Then The status code is 403

  Scenario: I have no valid token so I cannot edit a valid activity
    Given I have no valid token
    When I send a PUT request to /activity with an edited activity for id = 4
    Then The status code is 401

  Scenario: I am just a user so I cannot edit a valid activity
    Given I am connected as a user
    When I send a PUT request to /activity with an edited activity for id = 4
    Then The status code is 403

  Scenario: I have no valid token so I cannot delete a valid activity
    Given I have no valid token
    When I send a DELETE request to /activity for id = 4
    Then The status code is 401

  Scenario: I am just a user so I cannot delete a valid activity
    Given I am connected as a user
    When I send a DELETE request to /activity for id = 4
    Then The status code is 403




  Scenario: I am an admin then I can add activities
    Given I am connected as an admin
    When I send a POST request to /activity with a new activity
    Then The status code is 204

  Scenario: I am an admin but I cannot insert a malformed activity
    Given I am connected as an admin
    When I send a POST request to /activity with a malformed activity
    Then The status code is 400

  Scenario: I am an admin then I can edit a valid activity
    Given I am connected as an admin
    When I send a PUT request to /activity with an edited activity for id = 4
    Then The status code is 204

  Scenario: I am an admin but I cannot edit an invalid activity
    Given I am connected as an admin
    When I send a PUT request to /activity with an edited activity for id = 352
    Then The status code is 404

  Scenario: I am an admin then I can delete a valid activity
    Given I am connected as an admin
    When I send a DELETE request to /activity for id = 4
    Then The status code is 204

  Scenario: I am an admin but I cannot delete an invalid activity
    Given I am connected as an admin
    When I send a DELETE request to /activity for id = 314
    Then The status code is 404



