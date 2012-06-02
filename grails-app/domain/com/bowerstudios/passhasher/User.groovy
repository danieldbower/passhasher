package com.bowerstudios.passhasher

class User {

	String name
	
	static hasMany = [places: Place]
	
	Date dateCreated
	Date lastUpdated
	
    static constraints = {
		name blank: false, unique: true
    }
	
	static mapping = {
		sort "name"
	}
	
	String toString(){
		return name
	}
	
	public static jsonProperties = { User user ->
		['name': user.name, 'places': user.places]
	}
}
