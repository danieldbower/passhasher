class AddPassLengthColumnToPlacesTable < ActiveRecord::Migration
  def self.up
    add_column(:places, :pass_length, :integer, {:default => 16, :null => false})
  end

  def self.down
    remove_column(:places, :pass_length)
  end
end
