package com.bowerstudios.passhasher

import grails.converters.JSON

import com.bowerstudios.passhasher.json.*

class UserController {

	static allowedMethods = [save: "POST", delete: "POST"]

	UserService userService

	/**
	 * Give a hint for the index action ("show")
	 */
    def index() {
        redirect(action: "show", params: params)
    }

	/**
	 * List users in the system
	 */
	// :TODO url user/
	// :TODO Only admin can run this method
	def list() {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)

		render new PagedResponse(User.list(params), User.count(), "Showing list of profiles in the system") as JSON
	}

	/**
	 * Display the attributes of a single User
	 */
	// :TODO if role is admin, allow to lookup by id, otherwise, must be logged in and pull id from session
	// :TODO url user/id as get
	def show() {
		User user = userService.lookup(params.id)

		if(user){
			render new SingleResponse(user, "Showing profile for $user") as JSON
		}else{
			render new FailureResponse("User not found") as JSON
		}
	}

	/**
	 * Create a new user and fill with defaults - not persisted
	 */
	// :TODO url user/create
	// :TODO if role is admin, allow, otherwise, disallow, unless user is anonymous and creating a profile
	def create() {
		User user = new User(params)
		
		render new SingleResponse(user, "New Profile with default values") as JSON		
	}
	
	// :TODO if role is admin, allow to update any property, otherwise, must be logged in and pull id from session.  And then can only delete some properties.
	// :TODO url user/id as post
	def save() {
		User user = userService.lookup(params.id)
		
		if (!user) {
			render new FailureResponse("User not found") as JSON
			return
		}

		user.properties = params

		if (!userService.save(user)) {
			// :TODO pull validation messages from user object
			render new FailureResponse("Unable to save profile")
		}

		render new SingleResponse(user, "Saved profile for $user") as JSON
	}

	// :TODO if role is admin, allow to delete a user, otherwise, must be logged in and pull id from session
	// :TODO url user/id as delete
	def delete() {
		User user = userService.lookup(params.id)
		
		if (!user) {
			render new FailureResponse("User not found") as JSON
			return
		}

		if(userService.delete(user)){
			render new SingleResponse(null, "Successfully deleted profile of $user") as JSON
		}else{
			render new FailureResponse("Unable to delete profile")
		}
	}
}
