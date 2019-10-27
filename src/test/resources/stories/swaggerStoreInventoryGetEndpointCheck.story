Narrative:
As a user
I want to see that inventory is working
So that I can send GET call to SwaggerPetStore

Scenario: Check inventory in the SwaggerPetStore using Get call
Given I check Inventory status code
When I check headers of the call
Then I check that call body contains 'sold' with '129' value