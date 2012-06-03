package com.bowerstudios.passhasher

import grails.converters.JSON

import com.bowerstudios.passhasher.json.*

class UserController {

	static allowedMethods = [save: "POST", delete: "POST"]

	UserService userService

	/**
	 * Get a list of users in the system
	 */
	// :TODO Only admin can run this method
	def list() {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)

		render new PagedResponse(User.list(params), User.count(), "Showing list of profiles in the system") as JSON
	}

	/**
	 * Display the attributes of a single user
	 */
	// :TODO if role is admin, allow to lookup by id, otherwise, must be logged in and pull id from session
	def show() {
		User user = userService.lookup(params.id)

		if(user){
			render new SingleResponse(user, "Showing profile for $user") as JSON
		}else{
			render new FailureResponse("User not found") as JSON
		}
	}

	// :TODO if role is admin, allow to update any property, otherwise, must be logged in and pull id from session.  And then can only delete some properties.
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
