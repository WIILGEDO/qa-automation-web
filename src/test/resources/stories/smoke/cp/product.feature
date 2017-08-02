@product @smoke @saucelabs
Feature: Product details page


  @smokeRefactored
  Scenario: Product details page data is correct for direct product link
    When go to product page '9999999999994'
    Then verify product details page data is 'E2E Products 102' '1' 'noon' '' '' 'Sky Blue'

  @smokeRefactored
  Scenario: Product details page is opened
    Given open consumer platform page
    When open search and enter session text
    And search for entered term
    And select session product
    Then verify product details page data is correct


