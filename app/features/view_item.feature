Feature: View item feature

  @VI001
  Scenario: VI001 - Select and view item data
    Given I have successfully signed in
    When  I select the first item in the list
    Then  I should see the correct item page
     And  I should see the item description