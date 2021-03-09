@TrainticketBookingAutomationfortwoway
Feature: To verify the details of train ticket booking for two way travel


  Scenario: The user verifies the details of train ticket booking for two way travel
    Given The user is on Blibli website
    When The user clicks on train tab
    And User fills details of the train for two way travel
    And User clicks book tickets
    And User selects the trains
    And User verifes the customer details page of the trains
    And User fills the customer details
    And User verifies the details of the customer
    And User fills in passenger details
    And User verifies the passenger details of the trains
    And User proceeds to payments
    And User checks details in payments page of the trains
    Then User verifies the details of the train booking


