###
#
# When querying for elements we can either use the predefined functions such as 'element_exists' or
# query for the element using 'query' and then add the desired logic
#
# Example are shown below
###

Then(/^I should see a "(.*?)" widget of type "(.*?)" with text "(.*?)"$/) do |id, component, text|

  # Getting the element
  elements = query("android.widget.#{component} id:'#{id}'")

  raise "android.widget.#{component} with id: #{id} not found" if elements.empty?
  raise "more than one android.widget.#{component} with id: #{id} was found" if elements.length > 1

  element = elements.first

  raise "Expected text for android.widget.#{component} with id: #{id} to be '#{text}' instead '#{element['text']}'" unless element['text'].eql?(text)
end


Then(/^I should see a "(.*?)" widget of type "(.*?)" with hint "(.*?)"$/) do |id, component, text|

  # Using predefined function to check if the specific element exists
  # hints are not shown as attributes when querying for an element in the console, but they can be used as a criteria param in the query
  raise "Element android.widget.#{component} with id: #{id} and '#{text}' not found" unless element_exists("android.widget.#{component} id:'#{id}' hint:'#{text}'")
end
