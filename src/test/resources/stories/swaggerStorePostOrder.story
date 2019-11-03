Narrative:
As a user
I want to place an order
So that I can receive my order in future

Scenario: Create an order with Id from range (1-10)
Given I place an order to swagger store with '10', '1'
When I check call headers for 'POST Order'
Then I check status code for 'POST Order'

Scenario: Create an order with Id from range (1-10)
Given I place an order to swagger store with '5', '7'
When I check call headers for 'POST Order'
Then I check status code for 'POST Order'

Scenario: Create an order with Id from range (1-10)
Given I place an order to swagger store with '1', '10'
When I check call headers for 'POST Order'
Then I check status code for 'POST Order'

Scenario: Order can not be created with negative Id
Given I place an order to swagger store with '-11', '4'
When I check call headers for 'POST Order'
Then I check status code for 'POST Order'

Scenario: Order can not be created with zero Id
Given I place an order to swagger store with '0', '5'
When I check call headers for 'POST Order'
Then I check status code for 'POST Order'

Scenario: Order can not be created with negative Quantity
Given I place an order to swagger store with '7', '-4'
When I check call headers for 'POST Order'
Then I check status code for 'POST Order'

Scenario: Order can not be created with out of range Quantity
Given I place an order to swagger store with '8', '55'
When I check call headers for 'POST Order'
Then I check status code for 'POST Order'
