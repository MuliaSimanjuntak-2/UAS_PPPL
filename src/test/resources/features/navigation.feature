Feature: Navigation Bar Functionality
  As a user, I want to navigate through the Sarponesia website using the navigation bar.

  Scenario: Navigate to Layanan & Produk Kopi
    Given I am on the home page
    When I click on the "Layanan & Produk Kopi" link
    Then I should be redirected to the services page
