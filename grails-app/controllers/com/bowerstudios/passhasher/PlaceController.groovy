package com.bowerstudios.passhasher

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

import com.bowerstudios.passhasher.json.FailureResponse
import com.bowerstudios.passhasher.json.PagedResponse
import com.bowerstudios.passhasher.json.SingleResponse


@Secured(['ROLE_PASSHASHER', 'ROLE_PASSHASHER_ADMIN'])
class PlaceController {

	static allowedMethods = [save: "POST", delete: "POST"]

	UserService userService
	PlaceService placeService
	
	/**
	 * Give a hint for the index action ("list")
	 */
	def index() {
		redirect(action: "list", params: params)
	}

	/**
	 * List places by user 
	 */
	// :TODO url user/userId/place/
	def list() {
		User user = userService.effectiveUser(params.userId)
		
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		
		// :TODO only search by user, and paging
		render new PagedResponse(Place.list(params), Place.count(), "Showing list of places for profile $user") as JSON
	}

	/**
	 * Display the attributes of a single Place
	 */
	// :TODO url user/userId/place/id
	def show() {
		User user = userService.effectiveUser(params.userId)
		
		Place place = placeService.lookup(params.id)
		
		if (place) {
			if(place.user!=user){
				render new FailureResponse("Not Permitted") as JSON
				return
			}
			render new SingleResponse(place, "Showing place $params.id") as JSON
			return
		}else{
			render new FailureResponse("Place not found") as JSON
			return
			
			// :TODO proper way to return a string
			//message(code: 'default.not.found.message', args: [message(code: 'place.label', default: 'Place'), params.id])
		}
	}
	
	/**
	 * Create a new place and fill with defaults
	 */
	// :TODO url user/userId/place/create
	def create() {
		User user = userService.effectiveUser(params.userId)
		
		Place place = new Place(params)
		
		place.encodingChars = place.encodingChars ?: Place.DEFAULT_ENCODING_CHARS
		place.passLength = place.passLength ?: Place.DEFAULT_PASS_LENGTH
		place.hashTimes = place.hashTimes ?: Place.defaultHashTimes()
		place.user = user
		
		if(!place.save()){
			render new FailureResponse("Failed to persist place") as JSON
		}
		
		render new SingleResponse(place, "New Place with default values") as JSON
	}

	// :TODO url user/userId/place/id as post
	def save() {
		User user = userService.effectiveUser(params.userId)

		Place place = placeService.lookup(params.id)
		
		if (!place) {
			render new FailureResponse("Place not found") as JSON
			return
		}

		if(place.user!=user){
			render new FailureResponse("Not permitted") as JSON
			return
		}
		
		place.properties = params

		if (!placeService.save(place)) {
			// :TODO pull validation messages from place object
			render new FailureResponse("Unable to save profile")
			return
		}

		render new SingleResponse(place, "Saved place: $place") as JSON
	}
	
	// :TODO url user/userId/place/id as delete
	def delete() {
		User user = userService.effectiveUser(params.userId)

		Place place = placeService.lookup(params.id)
		
		if (!place) {
			render new FailureResponse("Place not found") as JSON
			return
		}
		
		if(place.user!=user){
			render new FailureResponse("Not permitted") as JSON
			return
		}

		if(placeService.delete(place)){
			render new SingleResponse(null, "Successfully deleted $place") as JSON
		}else{
			render new FailureResponse("Unable to delete place")
		}
	}
}
