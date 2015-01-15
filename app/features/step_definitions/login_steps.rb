Given(/^I enter "(.*?)" into the Username field$/) do |username|
  step %Q|I enter text "#{username}" into field with id "enter_username"|
end


Given(/^I enter "(.*?)" into the Password field$/) do |password|
  enter_text("android.widget.EditText marked:'enter_password'", "#{password}")
end


When(/^I press the sign in button$/) do
  touch("android.widget.Button id:'sign_in_button'")
end


Then(/^I should see the main page$/) do
  wait_for_element_exists("android.widget.LinearLayout id:'main_layout'")
end


Then(/^I should see a "(.*?)" message$/) do |message|
  wait_for_text(message)
end


Given(/^I have successfully signed in$/) do
  step %|I enter "bbm" into the Username field|
  step %|I enter "pass" into the Password field|
  step %|I press the sign in button|
end
