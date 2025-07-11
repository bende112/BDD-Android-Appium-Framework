Feature: Checkout functionality

  Background:
    Given I launched app and I am on the product list screen

#  Scenario: Add a single product to checkout
#    Given I launched app and I am on the product list screen
#    When I add "Sauce Labs Backpack" to the cart
#    And I navigates to the checkout screen
#    And I enter checkout details "Bende", "Nku", and "NR9"
#    Then "Sauce Labs Backpack" should be displayed in the checkout list

  Scenario: Add a single product to checkout
    When I add "Sauce Labs Backpack" to the cart
    And I navigates to the checkout screen
    When I enter checkout details "Bende", "Nku", and "NR9"
#    Then "Sauce Labs Backpack" should be displayed in the checkout overview
#    When "Sauce Labs Backpack" and its price "$29.99" should be displayed in the checkout overview
#    Then "Sauce Labs Backpack" should be displayed in the checkout list
#    And the total price should be greater than 0

#  Scenario: Add multiple products to checkout
#    When I add "Product 1" to the cart
#    And I add "Product 2" to the cart
#    And I navigates to the checkout screen
#    Then "Product 1" and "Product 2" should be displayed in the checkout list
#    And the total price should be the sum of the products' prices
#
#  Scenario: Delete a product from checkout
#    Given I have added "Product 1" and "Product 2" to the cart
#    When I deletes "Product 1" from the checkout
#    Then "Product 1" should not be displayed in the checkout list
#    And "Product 2" should still be displayed in the checkout list
#
#  Scenario: Proceed to checkout with an empty cart
#    When I navigates to the checkout screen without adding products
#    Then an error message "Your cart is empty" should be displayed
#
#  Scenario: Add out-of-stock product to cart
#    When I try to add "Out of Stock Product" to the cart
#    Then an error message "Product is out of stock" should be displayed
#    And the product should not be added to the cart
