Feature: User Registration, Login, and Purchase

  Scenario: User registration and login
    Given I open the browser
    And I navigate to the registration page
    When I enter valid registration details
    And I submit the registration form
    Then I should see a successful registration message
    And I navigate to the login page
    When I enter valid login details
    And I submit the login form
    Then I should see a welcome message
    When I click on HTC One M9
    Then I should see the product details
    When I add the product to the cart
    And I navigate to the cart
    Then I should see the product in the cart
    When I place the order
    And I fill in the order details
    And I purchase the order
    Then I should see the purchase confirmation
    And I close the browser
