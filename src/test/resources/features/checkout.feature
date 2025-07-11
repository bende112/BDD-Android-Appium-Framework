Feature: Checkout functionality

  Background:
    Given I launched app and I am on the product list screen

  Scenario: Add a single product to checkout
    When I add "Sauce Labs Backpack" to the cart
    And I navigates to the checkout screen
    When "Sauce Labs Backpack" and its price "$29.99" should be displayed in the checkout overview
    And I click the checkout button
    When I enter checkout details "Bende", "Nku", and "NR9"

  Scenario: Add multiple products to checkout
    When I add "Sauce Labs Backpack" to the cart
    And I add "Sauce Labs Bike Light" to the cart
    And I navigates to the checkout screen
    And "Sauce Labs Backpack" and "Sauce Labs Bike Light" should be displayed in the checkout list
    When I remove "Sauce Labs Backpack" from the checkout list
    Then "Sauce Labs Bike Light" and its price "$9.99" should be displayed in the checkout overview

  Scenario: Delete a product from checkout
    Given I add "Sauce Labs Backpack" to the cart
    And I navigates to the checkout screen
    When I remove "Sauce Labs Backpack" from the checkout list
    Then "Sauce Labs Backpack" should not be displayed in the checkout list

