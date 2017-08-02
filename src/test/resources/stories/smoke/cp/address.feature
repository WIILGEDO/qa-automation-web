@address @smoke @saucelabs
Feature: Address book
  Background:
    Given open consumer platform page


  Scenario: Add new address
    When create registered user
    And login registered user
    And open my account
    And open address book
    And open add first address
    And locate me
    And complete location selection
    And fill address data Building name: 'BuildingName' Apartment: '1' Floor: '2' AreaName: 'AreaName' Landmark: 'Landmark' Phone: '971551112233' Name: 'Name'
    Then verify address is present: 'SESSION_DATA'

  @smokeRefactored
  Scenario: Add new address for hardcoded user
    When login with 'testnoon10@gmail.com' and password 'Test1234'
    And open my account
    And open address book
    And open add new address
    And locate me
    And complete location selection
    And fill address data Building name: 'BuildingName' Apartment: '1' Floor: '2' AreaName: 'AreaName' Landmark: 'Landmark' Phone: '971551112233' Name: 'Name'
    Then verify address is present: 'SESSION_DATA'



