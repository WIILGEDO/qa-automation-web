@categories @smoke @saucelabs
Feature: Categories
  Background:
    Given open consumer platform page

  @smokeRefactored
  Scenario: Categories navigation labels are correct
    Then verify Categories header: 'Fashion' 'Books' 'Toys' 'Beauty' 'Electronics' 'Home & Garden' 'Sports' 'Baby'

  @smokeRefactored
  Scenario: Navigate to Fashion category page
    When open category 'Fashion'
    Then verify category opened 'Fashion'

  @smokeRefactored
  Scenario: Navigate to Books category page
    When open category 'Books'
    Then verify category opened 'Books'

  @smokeRefactored
  Scenario: Navigate to Toys category page
    When open category 'Toys'
    Then verify category opened 'Toys'

  @smokeRefactored
  Scenario: Navigate to Beauty category page
    When open category 'Beauty'
    Then verify category opened 'Beauty'

  @smokeRefactored
  Scenario: Navigate to Electronics category page
    When open category 'Electronics'
    Then verify category opened 'Electronics'

  @smokeRefactored
  Scenario: Navigate to Home & Garden category page
    When open category 'Home & Garden'
    Then verify category opened 'Home & Garden'

  @smokeRefactored
  Scenario: Navigate to Sports category page
    When open category 'Sports'
    Then verify category opened 'Sports'





