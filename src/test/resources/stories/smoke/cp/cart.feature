@cart @smoke @saucelabs
Feature: Test CART
  Background:
    Given open consumer platform page

  # Unregistered User
  @smokeRefactored
  Scenario: Add session product to cart for unregistered user
    When open search and enter session text
    And search for entered term
    And select session product
    Then add current product to cart
    And open cart
    And verify product is in cart
    And remove session product from cart

  @smokeRefactored
  Scenario: Increase / decrease product quantity in cart for unregistered user
    When open search and enter session text
    And search for entered term
    And select session product
    Then add current product to cart
    And open cart
    And verify product is in cart
    And increase session product quantity in cart
    And increase session product quantity in cart
    And verify there are '3' items for product in cart
    And decrease session product quantity in cart
    And verify there are '2' items for product in cart
    And decrease session product quantity in cart
    And verify there are '1' items for product in cart

  @smokeRefactored
  Scenario: Remove session product from cart for unregistered user
    When open search and enter session text
    And search for entered term
    And select session product
    And add current product to cart
    And open cart
    And verify product is in cart
    When remove session product from cart
    Then verify cart is empty

  # Registered User
  @smokeRefactored
  Scenario: Add session product to cart for registered user
    When login with 'testnoon10@gmail.com' and password 'Test1234'
    When open search and enter session text
    And search for entered term
    And select session product
    Then add current product to cart
    And open cart
    And verify product is in cart
    And remove session product from cart

  @smokeRefactored
  Scenario: Increase / decrease product quantity in cart for registered user
    When login with 'testnoon10@gmail.com' and password 'Test1234'
    When open search and enter session text
    And search for entered term
    And select session product
    Then add current product to cart
    And open cart
    And verify product is in cart
    And increase session product quantity in cart
    And increase session product quantity in cart
    And verify there are '3' items for product in cart
    And decrease session product quantity in cart
    And verify there are '2' items for product in cart
    And decrease session product quantity in cart
    And verify there are '1' items for product in cart
    And remove session product from cart

  @smokeRefactored
  Scenario: Remove session product from cart for registered user
    When login with 'testnoon10@gmail.com' and password 'Test1234'
    When open search and enter session text
    And search for entered term
    And select session product
    And add current product to cart
    And open cart
    And verify product is in cart
    When remove session product from cart
    Then verify cart is empty



    #    Cart tests for registered user [new user] [session data]:
  @ignore
  Scenario: Test scenarios for registered user [session data]
    When create registered user
    And login registered user
    When open search and enter session text
    And search for entered term
    And select session product
    Then add current product to cart
    And verify product is in cart
    And increase session product quantity in cart
    And increase session product quantity in cart
    And verify there are '3' items for product in cart
    And decrease session product quantity in cart
    And verify there are '2' items for product in cart
    And decrease session product quantity in cart
    And verify there are '1' items for product in cart
    And remove session product from cart
    And verify cart is empty

