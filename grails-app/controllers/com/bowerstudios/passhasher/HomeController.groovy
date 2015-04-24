package com.bowerstudios.passhasher

import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService

import grails.plugin.springsecurity.SpringSecurityUtils

import com.bowerstudios.compare.NaturalSort

class HomeController {
	
	PlaceService placeService
	UserService userService

	@Secured(['ROLE_PASSHASHER', 'ROLE_PASSHASHER_ADMIN'])
	def index() {
		User user = userService.effectiveUser(params.userId)
		List sortedPlaces = []
		
		if(user){
			sortedPlaces = user.places.sort(false, new NaturalSort())
			log.debug("Current user $user accessing home page")
		}
		
		[userInstance:user, 
			sortedPlaces: sortedPlaces]
	}
	
	/**
	 * Display a form for creating a new place
	 * @return
	 */
	@Secured(['ROLE_PASSHASHER', 'ROLE_PASSHASHER_ADMIN'])
	def createPlace(){
		User user = userService.effectiveUser(params.userId)
		
		Place place = new Place(
				name:'ex: facebook or http://facebook.com',
				description:'ex: Description of place and possibly username')
		
		[userInstance:user, 
			place:place]
	}
	
	/**
	 * Save a new place for the user
	 */
	@Secured(['ROLE_PASSHASHER', 'ROLE_PASSHASHER_ADMIN'])
	def savePlace(){
		User user = userService.effectiveUser(params.userId)
		
		Place place = new Place(params)
		
		if (!placeService.save(place, user, params.version)) {
			render(view:'createPlace', model:[place:place])
			return
		}
		redirect(action:"index")
	}
	
	@Secured('permitAll')
	def about(){ }

	@Secured('permitAll')
	def todo(){ }
}
