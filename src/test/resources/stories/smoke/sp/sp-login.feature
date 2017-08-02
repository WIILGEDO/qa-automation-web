@seller
Feature: Test Login Seller
  Background:
    Given open noon seller page


  Scenario: Login with valid email on seller platform - hardcoded user
    When SP login by email or phone 'testnoon10@gmail.com' 'Test1234'
    Then verify user is logged in to SP
#    And sign out from user

  Scenario: Login with valid email and select seller - hardcoded user
    When SP login by email or phone 'testnoon10@gmail.com' 'Test1234'
    Then verify user is logged in to SP
    And SP select approved seller
    And SP verify approved seller page is loaded



