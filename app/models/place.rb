class Place < ActiveRecord::Base
  belongs_to :user

  def mine? user
    self.user.id==user.id
  end
end
