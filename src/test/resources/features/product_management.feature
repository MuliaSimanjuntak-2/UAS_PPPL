Feature: Product Management
  As an admin
  I want to manage products
  So that I can maintain the product catalog

  Scenario: Adding a new product
    Given I am on the product management page
    When I click the add product button
    Then the add product modal should be displayed
    When I fill in the product form with name "Test Product", category "Pupuk", description "Test Description", price "100000", stock "50", and image "test image/img.png"
    And I submit the product form
    Then the product "Test Product" should be displayed in the table

  Scenario: Adding a product with an empty name field
    Given I am on the product management page
    When I click the add product button
    Then the add product modal should be displayed
    When I fill in the product form with name "", category "Pupuk", description "Produk tanpa nama", price "50000", stock "20", and image "test image/img.png"
    And I submit the product form
    Then I should see a validation error message saying "Nama produk wajib diisi"

  Scenario: Switching product category tabs
    Given I am on the product management page
    When I switch to the "Benih" tab
    Then I should see products in the "Benih" category
    When I switch to the "Kopi" tab
    Then I should see products in the "Kopi" category

  Scenario: Deleting a product
    Given I am on the product management page
    When I select the first product
    And I click the delete button
    Then the product count should decrease

  Scenario: Trying to delete without selecting any product
    Given I am on the product management page
    When I click the delete button without selecting any product
    Then I should see an alert saying no product was selected

  Scenario: Searching for a product
    Given I am on the product management page
    When I search for "Test Product"
    Then I should see products matching "Test Product"