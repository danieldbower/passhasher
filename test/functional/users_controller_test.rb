require 'test_helper'

class UsersControllerTest < ActionController::TestCase
  test "should get user page when logged in" do
    get :show, {:user_id => users(:user_one).id}, {'user_id' => users(:user_one).id}
    assert_response :success
  end

  test "should get blank user page when not logged in" do
    get :show
    assert_response :success
  end
end
