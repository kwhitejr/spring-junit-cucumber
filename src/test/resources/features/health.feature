Feature: Health Check Endpoint
  As a system administrator
  I want to check the health status of the application
  So that I can monitor if the service is running properly

  Scenario: Health endpoint returns successful response
    Given the application is running
    When I call the health check endpoint
    Then the response status should be 200
    And the response should contain status "UP"
    And the response should contain service name "spring-junit-cucumber-demo"
    And the response should contain a timestamp

  Scenario: Health endpoint returns valid JSON structure
    Given the application is running
    When I call the health check endpoint
    Then the response should be valid JSON
    And the response should have the required fields