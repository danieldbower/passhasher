class UsersController < ApplicationController
  def show
    if(current_user)
      @places = Place.places_for_user current_user
    end
  end
end
