#Automated Inbounding in PRODUCTION si not possible as of now - TBD

@inbound @ignore


Feature: Feature Test Inbound
  Background:
      Given open Supply Chain main page

    Scenario:
      When login to supply chain
      Then open inbound process
      And open inbound dock process
      And Enter STN number
      And Inbound mass receive
      And Putaway sortable


