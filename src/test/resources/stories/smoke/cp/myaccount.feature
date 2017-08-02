@smoke @myaccount @saucelabs
Feature: My account
  Background:
    Given open consumer platform page


#    My account tests for existing registered user [ hardcoded user ]

  @smokeRefactored
  Scenario: Account page is loaded with navigation tabs
    When login with 'testnoon10@gmail.com' and password 'Test1234'
    And open my account
    Then verify account page is visible

  @smokeRefactored
  Scenario: Profile page shows my name and email
    When login with 'testnoon10@gmail.com' and password 'Test1234'
    And open my account
    And open my profile
    Then verify profile page has user name: 'John Smith' and email: 'testnoon10@gmail.com'

    
#    My account test for newly registered user
    @ignore
  Scenario: Verify my account side menu items for newly registered user
    When create registered user
    And login registered user
    And open my account
    Then verify account page is visible


