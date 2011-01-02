require 'test_helper'

class PlacesControllerTest < ActionController::TestCase
  setup do
    @place = places(:place_one)
  end

  test "should get new" do
    get :new, {:user_id => users(:user_one).id}, {'user_id' => users(:user_one).id}
    assert_response :success
  end

  test "should create place" do
    assert_difference('Place.count') do
      post :create, {:place => @place.attributes, :user_id => users(:user_one).id}, {'user_id' => users(:user_one).id}
    end
  end

  test "should destroy place" do
    assert_difference('Place.count', -1) do
      delete :destroy, {:id => @place.to_param, :user_id => users(:user_one).id}, {'user_id' => users(:user_one).id}
    end
  end
end
