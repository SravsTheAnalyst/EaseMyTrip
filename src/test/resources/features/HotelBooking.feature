Feature: Hotel Guest Selection
  As a user of EaseMyTrip
  I want to increase the adult count in hotel booking
  So that I can book a room for a large group

  @HotelTest
  Scenario: Verify adult count selection in hotel booking 
    Given user opens hotel booking page
    When user selects adult count
    Then selected adult count should displayed correctly