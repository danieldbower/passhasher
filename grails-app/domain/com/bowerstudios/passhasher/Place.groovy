package com.bowerstudios.passhasher

import org.apache.commons.lang.math.RandomUtils;

class Place {
	public static final String DEFAULT_ENCODING_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890"
	public static final int DEFAULT_PASS_LENGTH = 8

	String name
	String description

	int hashTimes
	String encodingChars
	int passLength

	static belongsTo = [user: User]

	Date dateCreated
	Date lastUpdated

	static constraints = {
		name blank: false, unique: true
		hashTimes min:25
		encodingChars blank: false
		passLength min:4
	}

	static mapping = { sort "name" }

	public static int defaultHashTimes(){
		int i = RandomUtils.nextInt(100)
		return 17*((i<45)?(i+21):i)
	}

	String toString(){
		return name
	}
	
	public static jsonProperties = { Place place ->
		['name': place.name, 'description': place.description,
			'hashTimes':place.hashTimes, 'encodingChars':place.encodingChars,
			'passLength':place.passLength]
	}
}
