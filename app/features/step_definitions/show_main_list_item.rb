When(/^I select the first item in the list$/) do
  touch("android.widget.ListView id:'main_list_view' android.widget.RelativeLayout id:'main_list_item' index:0")
end

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
