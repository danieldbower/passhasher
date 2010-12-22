class UsersController < ApplicationController
  def show
    @places = Place.places_for_user current_user
  end
end
