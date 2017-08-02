@seller
Feature: Test add offer
  Background:
    Given open noon seller page

  Scenario: Add an offer - new user
    When SP register by email
    And verify user is logged in to SP
    And SP go to create new seller
    And SP enter company details data
    And SP enter bank details data
    And SP upload documents data
    And SP logout
    And SP login with session user
    Then SP verify pending approval
    And SP logout
    And SP approve seller
    And SP login with session user
    And SP verify seller approved
    And SP select approved seller
    And SP verify approved seller page is loaded
    And SP go to add an offer
    And SP add an offer 'cake'
    And SP logout
    And SP login with session user
    And SP select approved seller
    And SP verify approved seller page is loaded
    And SP inventory go to Manage Offers
    And SP search by GTIN
    And SP verify offer is present
    And SP select all offers
    And SP replenish stock
    And SP enter shipment details
    And SP print all labels
    And SP complete shipment
    And SP inventory verify shipment is present
    And SP cleanup seller offers
    And SP block seller


    @ignore
  Scenario: Add an offer - hardcoded ser
    When SP login by email or phone 'testnoon11+1491479074007@gmail.com' 'Test1234'
    Then verify user is logged in to SP
    And SP select approved seller
    And SP verify approved seller page is loaded
    And SP go to add an offer
    And SP add an offer 'cake'

    @ignore
  Scenario: Verify existing shipment - hardcoded user verify inventory shipments
#    When SP login by email or phone 'testnoon11+1490794501206@gmail.com' 'Test1234'
    When SP login by email or phone 'testnoon11+1491891679681@gmail.com' 'Test1234'
    Then verify user is logged in to SP
    And SP select approved seller
    And SP verify approved seller page is loaded
    And SP inventory go to Manage Shipments
#    And SP inventory verify shipment is present
    And SP inventory verify shipment with STN '1346' is present


  @ignore
  Scenario: Verify existing offer - hardcoded user verify inventory offers
    When SP login by email or phone 'testnoon11+1491899659483@gmail.com' 'Test1234'
    Then verify user is logged in to SP
    And SP select approved seller
    And SP verify approved seller page is loaded
    And SP inventory go to Manage Offers
    And SP verify my offer is present by GTIN: '5011275548901'
    And SP select all offers


#    And SP static search by GTIN: '5011275548901'
#    And SP select all offers
#    And SP replenish stock
#    And SP enter shipment details
#    And SP print all labels
#    And SP verify my offer is present by GTIN: '9780099428824'
#    And SP verify my offer is present by GTIN: '9780007309054'
#    And SP verify my offer is present by GTIN: '9780801874147'
#    And SP verify my offer is present by GTIN: '9780099428824'

  @ignore
  Scenario: Test scenario debug
    And SP login by email or phone 'testnoon11+1491908352741@gmail.com' 'Test1234'
    And SP select approved seller
    And SP verify approved seller page is loaded
    And SP inventory go to Manage Offers
#    And SP static search by GTIN: '5011275548901'
    And SP verify my offer is present by GTIN: '5011275548901'
    And SP select all offers
    And SP replenish stock
    And SP enter shipment details
    And SP print all labels
    And SP complete shipment
    And SP inventory verify shipment is present

#    And SP cleanup static seller offers user 'testnoon11+1491908352741@gmail.com'' 'Test1234'
#    And SP block static seller 'testnoon11+1491896193963@gmail.com' 'Test1234'

  @ignore
  Scenario: abc test
    When SP cleanup static seller offers user 'testnoon11+1491896193963@gmail.com' 'Test1234'
    Then SP block static seller 'testnoon11+1491896193963@gmail.com' 'Test1234'