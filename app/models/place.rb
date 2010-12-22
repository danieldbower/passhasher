class Place < ActiveRecord::Base
  include Comparable
  include Sort

  belongs_to :user

  def mine? user
    self.user.id==user.id
  end

  def <=>(other)
    sensible_compare(self.name, other.name) 
  end

  def self.places_for_user current_user
    places = Place.find_all_by_user_id(current_user.id)
    places.sort!
    places
  end
end
