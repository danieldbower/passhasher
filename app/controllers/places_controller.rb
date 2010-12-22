class PlacesController < ApplicationController
  def index
    @places = Place.find_all_by_user_id(current_user.id)
  end

  def new
    @place = Place.new
    @place.user = current_user
  end

  def create
    @place = Place.new(params[:place])
    @place.user = current_user

    if @place.save
      redirect_to(@place.user, :notice => "Place #{@place.name} was successfully created.")
    else
      render :action => "new"
    end
  end

  def destroy
    @place = Place.find(params[:id])
    if @place.mine? current_user
      @place.destroy
      redirect_to(@place.user, :notice => "Place #{@place.name} was successfully deleted.")
    else
      render :text => "Sorry, that's not your place."
    end
  end

end
