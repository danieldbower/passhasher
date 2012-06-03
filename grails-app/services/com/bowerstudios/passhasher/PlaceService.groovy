package com.bowerstudios.passhasher

import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException

class PlaceService {

	boolean save(Place place, Map params) {
		if (params.version) {
			def version = params.version.toLong()
			if (place.version > version) {
				place.errors.rejectValue("version", "default.optimistic.locking.failure",
						[ message(code: 'place.label', default: 'Place')]
						as Object[],
						"Another session has updated this Place while you were editing")
				return false
			}
		}
		
		return place.save(flush: true)
	}
	
	Place lookup(String id){
		if(id && id.isBigInteger()){
		
			Place place = Place.get(id)
			
			if (place) {
				return place
			}
		}
	}
	
	boolean delete(Place place){
		try {
			place.delete(flush: true)
			return true
		}
		catch (DataIntegrityViolationException e) {
			return false
		}
	}
}
