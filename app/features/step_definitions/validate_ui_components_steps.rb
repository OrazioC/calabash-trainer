Given(/^I should see a username input box$/) do
  raise "Username input box not visible" unless @current_page.has_username_input_box?
end


Given(/^I should see a password input box$/) do
  raise "Password input box not visible" unless @current_page.has_password_input_box?
end


Given(/^I should see a sign in button$/) do
  raise "Sign in button not visible" unless @current_page.has_sign_in_button?
end
