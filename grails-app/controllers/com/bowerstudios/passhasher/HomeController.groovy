package com.bowerstudios.passhasher

import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

import com.bowerstudios.compare.NaturalSort

class HomeController {
	
	UserService userService

	@Secured('permitAll')
	def index() {
		User user = userService.effectiveUser(params.userId)
		List sortedPlaces = []
		
		if(user){
			sortedPlaces = user.places.sort(new NaturalSort())
			log.debug("Current user $user accessing home page")
		}
		
		[userInstance:user, 
			defaultHashTimes: Place.defaultHashTimes(), 
			defaultPassLength: Place.DEFAULT_PASS_LENGTH, 
			defaultEncodingChars: Place.DEFAULT_ENCODING_CHARS,
			sortedPlaces: sortedPlaces]
	}
}
