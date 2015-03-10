require 'calabash-android/abase'

class Page < Calabash::ABase

  def method_missing sym, *args, &block
    send sym, *args, &block
  end

  def self.element(name, &block)
    define_method(name.to_s, &block)
  end

  class << self
    alias :value    :element
    alias :action   :element
    alias :trait    :element
    alias :activity :element
  end
end