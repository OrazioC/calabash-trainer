Feature: Sign in feature

  @SI001 @login
  Scenario: SI001 - Validate Sign in page
    Then I should see a "enter_username_label" widget of type "TextView" with text "Username"
     And I should see a "enter_username" widget of type "EditText" with hint "Please enter your username"
     And I should see a "enter_password_label" widget of type "TextView" with text "Password"
     And I should see a "enter_password" widget of type "EditText" with hint "Please enter your password"
     And I should see a "sign_in_button" widget of type "Button" with text "Sign in"


  @SI002 @login @smoke
  Scenario: SI002 - User inserts a valid username and password
    Given I am on the sign in page
      And I enter a valid username
      And I enter a valid password
    When  I press the sign in button
    Then  I should see the main page