package com.bowerstudios.passhasher

import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService

import grails.plugin.springsecurity.SpringSecurityUtils

import com.bowerstudios.compare.NaturalSort

class HomeController {
	
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
			defaultHashTimes: Place.defaultHashTimes(), 
			defaultPassLength: Place.DEFAULT_PASS_LENGTH, 
			defaultEncodingChars: Place.DEFAULT_ENCODING_CHARS,
			sortedPlaces: sortedPlaces]
	}
	
	def createPlace(){ }
	
	@Secured('permitAll')
	def about(){ }

	@Secured('permitAll')
	def todo(){ }
}
