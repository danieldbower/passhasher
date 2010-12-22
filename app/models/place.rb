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

end
