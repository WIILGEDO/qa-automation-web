@login @regression @saucelabs
Feature: Authentication Verification
  Background:
    Given open consumer platform page

  @smokeRefactored
  Scenario: Authenticate with valid email and password
    When login with 'testnoon10@gmail.com' and password 'Test1234'
    Then verify user logged in
    And sign out

  @smokeRefactored
  Scenario: Authenticate with valid phone number and password
    When login with '+971529961453' and password 'Password1'
    Then verify user logged in
    And sign out


### Negative Scenarios: ###

  @smokeRefactored
  Scenario Outline: Authenticate with invalid email
    When login with '<email>' and password '<password>'
    Then verify login failed alert is visible
    And return to home page
    Examples:
      | email                              | password                   |
      | nonregisteredemail@example.com     | invalid                    |
      | invalidemail@.com                  | invalid                    |
      | email@com                          | invalid                    |
      | !@#!@#!@#@ !@$$%&*.*^%)&^%@!       | invalid                    |
      | mytestemail.kk2+101@example.com    | invalid                    |

  @smokeRefactored
  Scenario Outline: Authenticate with valid email and invalid password
    When login with '<email>' and password '<password>'
    Then verify login failed alert is visible
    And return to home page
    Examples:
      | email                              | password                      |
      | testnoon10@gmail.com               | invalid                       |

  @smokeRefactored
  Scenario: Authenticate with empty password
    When login with 'testnoon10@gmail.com' and password ''
    Then verify empty password message is visible
    And return to home page

  @smokeRefactored
  Scenario Outline: Authenticate with incorrect format phone number or email
    When login with '<userName>' and password '<password>'
    Then verify invalid username message is visible
    And return to home page
  Examples:
    | userName                           | password                   |
    |                                    | invalid                    |
    | @gmail.com                         | invalid                    |
    | @ .                                | invalid                    |
    | +971-123-4567                      | invalid                    |

  @smokeRefactored
  Scenario Outline: Authenticate with phone number and invalid password
    When login with '<phone>' and password '<password>'
    Then verify login failed alert is visible
    And return to home page
    Examples:
      | phone                              | password                      |
      | 123456                             | invalid                       |
      | 0121234567                         | invalid                       |

  @sauceignore @smokeRefactored
  Scenario: Authenticate with Google account page is visible
    When login by google
    Then verify google authentication window is visible

  @ignore
  Scenario:  Authenticate with Facebook account page is visible
    When login by facebook
    Then verify facebook authentication window is visible

# above working tests @smokeRefactored

  # Does not work on CI  cause google prompts with anti-robot windows
  @sauceignore  @ignore
  Scenario: Login with Google account
    When login by google 'testnoon14@gmail.com ' 'W3lcome!'
    Then verify user logged in
    And sign out

  @smoke
  Scenario: Login with facebook account
    When login by facebook 'mytestemail.kk2@gmail.com' 'Test123123123'
    Then verify user logged in
    And sign out


#    Login test for registered user [existing hardcoded]:
  @ignore
  Scenario: Login with new email and password
    When register user with email
    And login registered user
    Then verify user logged in
    And sign out

