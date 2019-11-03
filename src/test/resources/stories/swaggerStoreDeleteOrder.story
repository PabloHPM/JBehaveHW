Narrative:
As a user
I want to delete my order
So that I can not veiw it in order list

Scenario: Check swagger Store with Id from range (1-10)
Given I place an order to swagger store with '1', '2'
Then I delete an order from the store with id '1'
