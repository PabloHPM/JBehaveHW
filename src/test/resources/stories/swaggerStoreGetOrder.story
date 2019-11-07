Narrative:
As a user
I want to find an order
So that I can veiw order detail

Scenario: Check swagger Store with Id from range (1-10)
Given I place an order to swagger store with '1', '2'
When I check call headers for 'GET Order'
Then I check status code for 'GET Order'

Scenario: Check swagger Store with Id from range (1-10)
Given I place an order to swagger store with '4', '2'
When I check call headers for 'GET Order'
Then I check status code for 'GET Order'

Scenario: Check swagger Store with Id from range (1-10)
Given I place an order to swagger store with '8', '2'
When I check call headers for 'GET Order'
Then I check status code for 'GET Order'

Scenario: Check swagger Store with Id from range (1-10)
Given I place an order to swagger store with '10', '2'
When I check call headers for 'GET Order'
Then I check status code for 'GET Order'

!-- Scenario: Check store with out of the range id
!-- Given I place an order to swagger store with '<id>', '<quantity>'
!-- When I check call headers for 'GET Order'
!-- Then I check status code for 'GET Order'
!--
!-- Examples:
!-- |<id>|<quantity>|
!-- |-11|2|
!-- |0|2|
!-- |11|2|
!-- |100|2|