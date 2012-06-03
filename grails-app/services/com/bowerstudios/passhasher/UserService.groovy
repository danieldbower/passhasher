package com.bowerstudios.passhasher

import org.springframework.dao.DataIntegrityViolationException

class UserService {
	
	boolean save(User user, Map params) {
		if (params.version) {
			def version = params.version.toLong()
			if (user.version > version) {
				user.errors.rejectValue("version", "default.optimistic.locking.failure",
						[ message(code: 'user.label', default: 'User')]
						as Object[],
						"Another user has updated this User while you were editing")
				return false
			}
		}
		
		return user.save(flush: true)
	}
	
	User lookup(String id){
		if(id && id.isBigInteger()){
		
			User user = User.get(id)
			
			if (user) {
				return user
			}
		}
	}
	
	boolean delete(User user){
		try {
			user.delete(flush: true)
			return true
		}
		catch (DataIntegrityViolationException e) {
			return false
		}
	}
	
}
