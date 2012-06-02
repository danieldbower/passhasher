package com.bowerstudios.passhasher

import grails.converters.JSON

import com.bowerstudios.passhasher.json.*

class UserController {

	static allowedMethods = [update: "POST", delete: "POST"]

	UserService userService

	/**
	 * Main entrance to the app.  A user sees their profile and all their places.
	 */
	def passhasher() {
		User user = userService.lookup(params.id)
			
		if (!user){
			//return the user if there is one
			return
		}
		
		[user: user]
	}
	
	/**
	 * Get a list of users in the system
	 * @return
	 */
	def list() {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		
		render new PagedResponse(User.list(params), User.count()) as JSON
	}

	def show() {
		User user = userService.lookup(params.id)
		
		if(user){
			render new SingleResponse(user) as JSON
		}else{
			render new FailureResponse("User not found") as JSON			
		}
	}

	def edit() {
		User user = userService.lookup(params.id)
		if (!user) {
			redirect(action: "list")
			return
		}

		[user: user]
	}

	def update() {
		User user = userService.lookup(params.id)
		if (!user) {
			redirect(action: "list")
			return
		}

		user.properties = params
		
		if (!userService.save(user)) {
			render(view: "edit", model: [user: user])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [
			message(code: 'user.label', default: 'User'),
			user.id
		])
		redirect(action: "show", id: user.id)
	}

	def delete() {
		User user = userService.lookup(params.id)
		if (!user) {
			redirect(action: "list")
			return
		}

		if(userService.delete(user)){
			redirect(action: "list")
		}else{
			redirect(action: "show", id: params.id)
		}
	}
}
