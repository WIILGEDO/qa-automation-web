@search @smoke @saucelabs
Feature: Test SEARCH feature
  Background:
    Given open consumer platform page



  Scenario: Test search for available product [session data]
    When open search and enter session text
    Then verify search hints titles are visible

  Scenario: Test search results number [session data]
    When open search and enter session text
    And search for entered term
    Then verify search results count '36'


  #
  #  Tests with hardcoded data below - TO BE REMOVED
  #
  @ignore
  Scenario Outline: Test search labels
    When open search and enter text '<product>'
    Then verify search hints titles are visible
  Examples:
  | product |
  | Scandal Parfum EDP |

    @ignore
    Scenario Outline: Test search results number
      When open search and enter text '<product>'
      And search for entered term
      Then verify search results count '1'
    Examples:
    | product |
    | Scandal Parfum EDP |





