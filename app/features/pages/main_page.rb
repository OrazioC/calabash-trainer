require_relative './page'

class MainPage < Page

  trait(:trait)                { "* id:'main_layout'"}

  element(:list_view)          { 'main_list_view' }

end