Feature: Login

  Scenario: Valid Login
    Given I open the app
    When I login with username "standard_user" and password "secret_sauce"
    Then I should see the products page
