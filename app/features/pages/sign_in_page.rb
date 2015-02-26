require_relative './page'

class SignInPage < Page

  trait(:trait)     { "* id:'sign_in_layout'"}

  element(:username_field)     { 'enter_username' }
  element(:password_field)     { 'enter_password' }
  element(:login_button)       { 'sign_in_button' }

  action(:touch_login_button)  { touch("* id:'#{login_button}'") }

  def enter_username(username)
    query("* id:'#{username_field}'", {:setText => username})
    hide_soft_keyboard
  end

  def enter_password(password)
    query("* id:'#{password_field}'", {:setText => password})
    hide_soft_keyboard
  end
end