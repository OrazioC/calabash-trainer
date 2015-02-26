Feature: Sign in feature

  @SI001 @login
  Scenario: SI001 - Validate Sign in page
    Given I am on the sign in page
     And I should see a username input box
     And I should see a password input box
     And I should see a sign in button


  @SI002 @login @smoke
  Scenario: SI002 - User inserts a valid username and password
    Given I am on the sign in page
      And I enter a valid username
      And I enter a valid password
    When  I press the sign in button
    Then  I should see the main page