Feature: Main list feature

  @ML001
  Scenario: ML001 - See the main list
    Given I have successfully signed in
      And I see the main page
    Then  I should see the main list of items
      And I should see at least 4 items in the list
