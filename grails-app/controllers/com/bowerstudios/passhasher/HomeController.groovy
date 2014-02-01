package com.bowerstudios.passhasher

import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

import com.bowerstudios.compare.NaturalSort

class HomeController {
	
	UserService userService
	SpringSecurityService springSecurityService

	@Secured('permitAll')
	def index() {
		User user
		List sortedPlaces = []
		
		if(SpringSecurityUtils.ifAnyGranted('ROLE_PASSHASHER_ADMIN') && params.id){
			log.info("Admin accessing as different user")
			user = userService.lookup(params.id)
		}else{
			user = springSecurityService.getCurrentUser()

			if(user){
				sortedPlaces = user.places.sort(new NaturalSort())
				log.debug("Current user $user accessing home page")
			}else{
				log.debug("No current User - anonymous")
			}
		}
		
		[userInstance:user, 
			defaultHashTimes: Place.DEFAULT_HASH_TIMES, 
			defaultPassLength: Place.DEFAULT_PASS_LENGTH, 
			defaultEncodingChars: Place.DEFAULT_ENCODING_CHARS,
			sortedPlaces: sortedPlaces]
	}
}
