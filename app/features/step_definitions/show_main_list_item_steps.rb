Then(/^I should see the correct item page$/) do
  #wait_for_text("Death Star")
  wait_for_element_exists("android.widget.TextView text:'Death Star'")
end

Then(/^I should see the item description$/) do
  pending # express the regexp above with the code you wish you had
end

Then(/^I should see the item image$/) do
  pending # express the regexp above with the code you wish you had
end
