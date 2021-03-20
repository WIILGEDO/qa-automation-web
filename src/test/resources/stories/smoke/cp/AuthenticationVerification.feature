@login @regression @saucelabs
Feature: Authentication Verification
  Background:
    Given open consumer platform page

  @smokeRefactored
  Scenario: Authenticate with valid email and password
    When login with 'wiilgedo10@gmail.com' and password 'a5557669'
    Then verify user logged in
    And sign out