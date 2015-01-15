Given(/^I see the main page$/) do
  step %|I should see the main page|
end


Then(/^I should see the main list of items$/) do
  raise "Element android.widget.ListView with id: main_list_view not found" unless element_exists("android.widget.ListView id:'main_list_view'")
end


Then(/^I should see at least (\d+) items in the list$/) do |expected_items_number|
  items_number = query("android.widget.ListView id:'main_list_view' android.widget.RelativeLayout id:'main_list_item'").size

  raise "Expected to see #{expected_items_number} elements in the main list instead #{items_number}" unless items_number >= Integer(expected_items_number)
end
