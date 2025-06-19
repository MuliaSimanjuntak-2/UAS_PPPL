Feature: Login Functionality
  As a user, I want to log in to the Sarponesia website to access my account.

  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter valid email "admin@example.com" and password "password"
    And I click the login button
    Then I should be redirected to the home page

  Scenario: Failed login with invalid credentials
    Given I am on the login page
    When I enter invalid email "invalid@example.com" and password "wrongpassword"
    And I click the login button
    Then I should see an error message "Invalid credentials."