package com.bowerstudios.passhasher

import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class HomeController {
	
	UserService userService
	SpringSecurityService springSecurityService

	@Secured('permitAll')
    def index() {
		User user
		
		if(SpringSecurityUtils.ifAnyGranted('ROLE_PASSHASHER_ADMIN')){
			user = userService.lookup(params.id)
		}else{
			user = springSecurityService.getCurrentUser()
		}
		
		[userInstance:user]
	}
}
