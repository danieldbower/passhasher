package com.bowerstudios.passhasher

class PlaceService {

	boolean save(Place place, User user, String newVersion) {
		if (newVersion) {
			def version = newVersion.toLong()
			if (place.version > version) {
				place.errors.rejectValue("version", "default.optimistic.locking.failure",
						[ message(code: 'place.label', default: 'Place')]
						as Object[],
						"Another session has updated this Place while you were editing")
				return false
			}
		}
		
		place.encodingChars = place.encodingChars ?: Place.DEFAULT_ENCODING_CHARS
		place.passLength = place.passLength ?: Place.DEFAULT_PASS_LENGTH
		place.hashTimes = place.hashTimes ?: Place.defaultHashTimes()
		place.user = user
		
		return place.save()
	}
}
