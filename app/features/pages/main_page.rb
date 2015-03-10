require_relative './page'

class MainPage < Page

  trait(:trait)                { "* id:'main_layout'"}

  element(:list_view)          { 'main_list_view' }
  element(:list_item)          { 'main_list_item' }

  def has_list_of_items?
    element_exists("* id:'#{list_view}'")
  end

  def count_items
    query("* id:'#{list_view}' * id:'#{list_item}'").size
  end

  def touch_item_in_list(position)
    touch("* id:'#{list_view}' * id:'#{list_item}' index:#{position-1}")
  end
end
