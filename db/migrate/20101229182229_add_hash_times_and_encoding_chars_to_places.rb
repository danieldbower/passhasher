class AddHashTimesAndEncodingCharsToPlaces < ActiveRecord::Migration
  def self.up
    add_column :places, :hash_times, :integer, {:default => 1000, :null => false }
    add_column :places, :encoding_chars, :string, {:default => '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz', :null => false }
  end

  def self.down
    remove_column :places, :encoding_chars
    remove_column :places, :hash_times
  end
end
