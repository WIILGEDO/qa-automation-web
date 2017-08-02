@seller
Feature: Test Register Seller
  Background:
    Given open noon seller page

  Scenario: Register new user [services generated gmail user]
    When SP register by email
    And verify user is logged in to SP
#    And SP go to edit existing seller
    And SP go to create new seller
    And SP enter company details data
    And SP enter bank details data
    And SP upload documents data
    And SP logout
    And SP login with session user
    Then SP verify pending approval
    And SP approve seller
    And SP verify seller approved

    @ignore
    Scenario: verify pending approval
#    When SP login by email or phone 'testnoon11+1490789550187@gmail.com' 'Test1234'
#      When SP login by email or phone 'testnoon11+1490771356916@gmail.com' 'Test1234'
#      When SP login by email or phone 'testnoon11+1490791043109@gmail.com' 'Test1234'
#      When SP login by email or phone 'testnoon11+1491198965673@gmail.com' 'Test1234'
#      When SP login by email or phone 'testnoon11+1491203136087@gmail.com' 'Test1234'
      When SP login by email or phone 'testnoon11+1491479074007@gmail.com' 'Test1234'
#    Then SP verify pending approval
    Then SP verify seller approved

  @ignore
  Scenario: Login existing user and register seller - hardcoded data
    When SP login by email or phone 'testnoon11+1491198264132@gmail.com' 'Test1234'
    And verify user is logged in to SP
#    And SP go to edit existing seller
    And SP go to create new seller
    And SP enter company details data
    And SP enter bank details data
    And SP upload documents data
    And SP logout
    And SP login by email or phone 'testnoon11+1491198264132@gmail.com' 'Test1234'
    Then SP verify pending approval

