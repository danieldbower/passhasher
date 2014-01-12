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
	// :TODO if role is admin, allow to list for all, otherwise must be logged in and pull id from session.
    def list() {
		User user = userService.lookup(params.userId)
		
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		
		// :TODO only search by user, and paging
		render new PagedResponse(Place.list(params), Place.count(), "Showing list of places for profile $user") as JSON
    }

	/**
	 * Display the attributes of a single Place
	 */
	// :TODO if role is admin, allow to lookup by id, otherwise must pull user id from session
	// :TODO url user/userId/place/id
    def show() {
        def place = placeService.lookup(params.id)
		
        if (place) {
			// :TODO assert that user has perms
			
			render new SingleResponse(place, "Showing place $params.id") as JSON
        }else{
			render new FailureResponse("Place not found") as JSON
			
			// :TODO proper way to return a string
			//message(code: 'default.not.found.message', args: [message(code: 'place.label', default: 'Place'), params.id])
        }
    }
	
	/**
	 * Create a new place and fill with defaults
	 */
	// :TODO url user/userId/place/create
	// :TODO allow admin to update a place for anyone, otherwise must be logged in and pull id from session.
    def create() {
		User user = userService.lookup(params.userId)
		
        Place place = new Place(params)
		
		place.encodingChars = Place.DEFAULT_ENCODING_CHARS
		place.passLength = Place.DEFAULT_PASS_LENGTH
		place.hashTimes = Place.defaultHashTimes()
		place.user = user
		
		render new SingleResponse(place, "New Profile with default values") as JSON		
    }

	// :TODO if role is admin, allow to update any property, otherwise must be logged in and pull id from session.
	// :TODO url user/userId/place/id as post
    def save() {
        Place place = placeService.lookup(params.id)
		
		if (!place) {
			render new FailureResponse("Place not found") as JSON
			return
		}

		// :TODO assert that user has perms
		
		place.properties = params

		if (!placeService.save(place)) {
			// :TODO pull validation messages from place object
			render new FailureResponse("Unable to save profile")
		}

		render new SingleResponse(place, "Saved profile for $place") as JSON
    }
	
	// :TODO if role is admin, allow to delete, otherwise must be logged in and pull id from session.
	// :TODO url user/userId/place/id as delete
    def delete() {
        Place place = placeService.lookup(params.id)
		
		if (!place) {
			render new FailureResponse("Place not found") as JSON
			return
		}
		
		// :TODO assert that user has perms

		if(placeService.delete(place)){
			render new SingleResponse(null, "Successfully deleted $place") as JSON
		}else{
			render new FailureResponse("Unable to delete place")
		}
    }
}
