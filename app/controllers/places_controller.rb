class PlacesController < ApplicationController
  respond_to :html, :json

  def new
    @place = Place.new
    @place.user = current_user
    @place.hash_times = 800 + rand(200)
    respond_with @place
  end

  def create
    @place = Place.new(params[:place])
    @place.user = current_user

    if @place.save
      respond_to do |format|
        format.html {
          redirect_to(@place.user, :notice => "Place #{@place.name} was successfully created.")
        }
        format.json { render :json => @places }
      end
    else
      respond_to do |format|
        format.html {
          render :action => "new"
        }
        format.json { render false }
      end
    end
  end

  def destroy
    @place = Place.find(params[:id])
    if @place.mine? current_user
      @place.destroy
      respond_to do |format|
        format.html {
          redirect_to(@place.user, :notice => "Place #{@place.name} was successfully deleted.")
        }
        format.json { render :json => @places }
      end
    else
      respond_to do |format|
        format.html {
          render :text => "Sorry, that's not your place."
        }
        format.json { render false }
      end
    end
  end

end
