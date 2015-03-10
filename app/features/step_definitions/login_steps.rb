Given(/^I am on the sign in page$/) do
  @current_page = page(SignInPage)
  raise "Expected to be on the 'sign in page'" unless @current_page.current_page?
end

Given(/^I enter a valid username$/) do
  @current_page.enter_username($valid_username)
end


Given(/^I enter a valid password$/) do
  @current_page.enter_password($valid_password)
end


When(/^I press the sign in button$/) do
  @current_page.touch_login_button
end


Then(/^I should see the main page$/) do
  @current_page = page(MainPage).await(timeout: 5)
end


Given(/^I have successfully signed in$/) do
  step %|I am on the sign in page|
  step %|I enter a valid username|
  step %|I enter a valid password|
  step %|I press the sign in button|
  step %|I should see the main page|
end
