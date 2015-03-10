Feature: Main list feature

  @ML001 @list
  Scenario: ML001 - See the main list
    Given I have successfully signed in
    Then  I should see the list of items


  @ML002 @list @smoke
  Scenario: ML002 - See the main list populated
    Given I have successfully signed in
    Then  I should see the list of items
      And I should see at least 4 items in the list


  @ML003 @list @smoke
    Scenario: ML003 - Touch the first item in the list
      Given I have successfully signed in
        And I see the list of items
      When  I touch the first item in the list
      Then  I should see the item page
