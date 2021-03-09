@TrainticketBookingAutomation
  Feature: To verify the details of train ticket booking


      Scenario: The user verifies the details of train ticket booking
            Given The user is on Blibli website
            When The user clicks on train tab
            And User fills details of the train
            And User clicks book tickets
            And User verifies the details of the travel in the train selection page
            And User selects the required train
            And User verifies the customer detail page
            And User fills the customer details
            And User verifies the passenger detail page
            And User fills in passenger details
            And User verifies the passenger details
            And User proceeds to payments
            And User checks details in payments page
            Then User verifies the details of the train booking




