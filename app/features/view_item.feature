Feature: View item feature

  @VI001 @todo_course_assignment
  Scenario: VI001 - Select and view item data
    Given I have successfully signed in
      And I see the list of items
    When  I select the "Death Star" item
    Then  I should see the "Death Star" item page
     And  I should see the item image
     And  I should see the item description