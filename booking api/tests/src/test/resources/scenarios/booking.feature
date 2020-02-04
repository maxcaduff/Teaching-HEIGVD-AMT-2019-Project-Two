
Feature: Logged users can manage their bookings


  Scenario: I don't have a valid user token so I cannot retrieve bookings.
    Given I have no valid token
    When I send a GET request to /booking/my
    Then The status code is 401

  Scenario: I don't have a valid user token so I cannot make bookings.
    Given I have no valid token
    When I send a POST request to /booking with a new Booking
    Then The status code is 401

  Scenario: I don't have a valid user token so I cannot delete bookings.
    Given I have no valid token
    When I send a DELETE request to /booking/12
    Then The status code is 401



  Scenario: I have a valid user token so I can access my bookings.
    Given I am connected as a user
    When I send a GET request to /booking/my
    Then I should get an empty table
    And The status code is 200

  Scenario: I have a valid user token so I can post a new booking.
    Given I am connected as a user
    When I send a POST request to /booking with a new Booking
    Then The status code is 204

  Scenario: I have a valid user token so I can add places to an existing booking.
    Given I am connected as a user
    When I send a POST request to /booking with a new Booking
    And I send a GET request to /booking/my
    Then I should get my updated booking
    And The status code is 200

  Scenario: I have a valid user token but I cannot book more than the number of places left.
    Given I am connected as a user
    When I send a POST request to /booking with too much places
    Then The status code is 403

  Scenario: I have a valid user token but I cannot do a booking with an invalid location
    Given I am connected as a user
    When I send a POST request to /booking with an invalid location ID
    Then The status code is 400

  Scenario: I have a valid user token but I cannot delete someone else's bookings
    Given I am connected as another user
    When I send a DELETE request to /booking/1
    Then The status code is 401

  Scenario: I have a valid user token so I can delete my bookings
    Given I am connected as a user
    When I send a DELETE request to /booking/1
    Then The status code is 204







