package com.bowerstudios.passhasher

class User {

	transient springSecurityService

	String name
	Date dateCreated
	Date lastUpdated
	
	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static hasMany = [places: Place, openIds: OpenId]
	
	static constraints = {
		name blank: false, unique: true
		username blank: false, unique: true
		password blank: false
	}
	
	static mapping = {
		sort "name"
		password column: '`password`'
	}
	
	String toString(){
		return name
	}
		
	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	public static jsonProperties = { User user ->
		['name': user.name, 'places': user.places]
	}
		
	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
