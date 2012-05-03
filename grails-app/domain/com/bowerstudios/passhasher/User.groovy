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
}
