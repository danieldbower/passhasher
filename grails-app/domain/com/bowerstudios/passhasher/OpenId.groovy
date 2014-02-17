package com.bowerstudios.passhasher

class OpenId {
	
	String url
	
	static belongsTo = [user: User]
	
	static constraints = {
		url unique: true
	}
}
