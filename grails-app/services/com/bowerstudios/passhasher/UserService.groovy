package com.bowerstudios.passhasher

import grails.plugin.springsecurity.SpringSecurityService

import grails.plugin.springsecurity.SpringSecurityUtils
import org.springframework.dao.DataIntegrityViolationException

class UserService {
	
	SpringSecurityService springSecurityService
	
	User lookup(String id){
		if(id && id.isBigInteger()){
		
			User user = User.get(id)
	
			if (user) {
				return user
			}
		}
	}

	User effectiveUser(String submittedId){
		User user
		
		if(SpringSecurityUtils.ifAnyGranted('ROLE_PASSHASHER_ADMIN') && submittedId){
			log.info("Admin accessing as different user")
			user = lookup(submittedId)
		}else{
			user = springSecurityService.getCurrentUser()
			if(!user){
				log.info("Anonymous access")
			}
		}
		
		return user
	}
	
}
