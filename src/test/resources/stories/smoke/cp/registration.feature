@registration @saucelabs @smoke
  Feature: User Registration
    Background:
      Given open consumer platform page

#    Will not work until e-mail creation is automated or OTP  service is changed (get straight from DB)
    @ignore
    Scenario: Register with valid email and password
      When enter random email, name "Testfirstname Testsurname" password "Noontest12" and submit
      And enter otp verification code and submit
      Then verify user logged in


    ### Negative Scenarios

    @smokeRefactored
    Scenario: Register with already registered email account
      When enter username 'Re-registration Email' email 'testnoon10@gmail.com' password 'Test1234' and submit
      Then verify duplicate account registration message is visible
      And return to home page

    @smokeRefactored
    Scenario: Register with already registered mobile account
      When enter username 'Re-registration Mobile' mobile '529961453' password 'Password1' and submit
      Then verify duplicate account registration message is visible
      And return to home page

    @smokeRefactored
    Scenario Outline: Register with invalid emails
      When enter username '<user>' email '<email>' password '<password>' and submit
      Then verify incorrect email message is visible
      And return to home page
      Examples:
        | user                             | email                          | password              |
        |  Empty email                     |                                | Noontest12            |
        |  No @ sign in email              | !#$%^gmail.com                 | Noontest12            |
        |  Symbols in email and wrong host | !#$%.^@.g.ma.i.l.com           | Noontest12            |
        |  No @ sign and no host in email  | gmail                          | Noontest12            |

    @smokeRefactored
    Scenario Outline: Register with incorrect password
      When enter username '<user>' email '<email>' password '<password>' and submit
      Then verify registration incorrect password message is visible
      And return to home page
      Examples:
        | user                             | email                          | password              |
        |  Empty password test             | example@example.com            |                       |
        |  Short password                  | example@example.com            | Abc123                |
        |  Short password                  | example@example.com            | abc                   |


    @smokeRefactored
    Scenario: Register with empty user name
      When enter username '' email 'mytestemail.kk@gmail.com' password 'Abc123123' and submit
      Then verify registration incorrect username message
      And return to home page

    @smokeRefactored
    Scenario Outline: Registration with incorrect phone number
      When enter username '<user>' mobile '<mobile>' password '<password>' and submit
      Then verify incorrect mobile phone message is visible
      And return to home page
      Examples:
        | user                                    | mobile                  | password              |
        | Country code in phone number            |+48123456789             |Abc123123              |
        | Country code in phone number and short  |+9711234567              |Abc123123              |
        | Country code in phone number            |+971001234567            |Abc123123              |
        | Characters in phone number              |abcdefghij               |Abc123123              |




