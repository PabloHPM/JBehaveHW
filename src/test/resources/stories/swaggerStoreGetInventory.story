Narrative:
As a user
I want to see that inventory is working
So that I can send GET call to SwaggerPetStore

Scenario: Receive available inventory from the SwaggerPetStore
Given I check that call body contains 'availble' with '4' value
When I check call headers for 'GET Inventory'
Then I check status code  for 'GET Inventory'
