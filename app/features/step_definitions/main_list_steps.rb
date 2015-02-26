Given(/^I see the main page$/) do
  step %|I should see the main page|
end


Then(/^I should see the list of items$/) do
  raise "List of items not found" unless @current_page.has_list_of_items?
end


Then(/^I should see at least (\d+) items in the list$/) do |expected_number_of_items|
  items_number = @current_page.count_items
  raise "Expected to see #{expected_number_of_items} elements in the main list instead #{items_number}" unless items_number >= Integer(expected_number_of_items)
end


Given(/^I see the list of items$/) do
  step %|I should see the list of items|
end


When(/^I touch the first item in the list$/) do
  position = 1
  @current_page.touch_item_in_list(position)
end


Then(/^I should see the item page$/) do
  @current_page = page(ItemPage).await(timeout: 5)
end

