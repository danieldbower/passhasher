import grails.converters.JSON

import com.bowerstudios.passhasher.Place
import com.bowerstudios.passhasher.Role
import com.bowerstudios.passhasher.User
import com.bowerstudios.passhasher.UserRole
import com.bowerstudios.passhasher.json.FailureResponse
import com.bowerstudios.passhasher.json.PagedResponse
import com.bowerstudios.passhasher.json.SingleResponse

class BootStrap {

    def init = { servletContext ->
		Role appRole = new Role(authority:"ROLE_PASSHASHER")
		appRole.save()
		
		User user1 = new User(name:"Daniel", username:"daniel", password:"daniel", 
			enabled:true, accountExpired:false, accountLocked:false, passwordExpired:false)
		user1.save()
		UserRole user1Role = new UserRole(user:user1, role:appRole)
		user1Role.save()
		
		User user2 = new User(name:"Ariel", username:"ariel", password:"ariel", 
			enabled:true, accountExpired:false, accountLocked:false, passwordExpired:false)
		user2.save()
		UserRole user2Role = new UserRole(user:user2, role:appRole)
		user2Role.save()
		
		User user3 = new User(name:"David", username:"david", password:"david", 
			enabled:true, accountExpired:false, accountLocked:false, passwordExpired:false)
		user3.save()
		UserRole user3Role = new UserRole(user:user3, role:appRole)
		user3Role.save()
		
		User user4 = new User(name:"Samuel", username:"samuel", password:"samuel", 
			enabled:true, accountExpired:false, accountLocked:false, passwordExpired:false)
		user4.save()
		UserRole user4Role = new UserRole(user:user4, role:appRole)
		user4Role.save()
		
		registerMarshallers()
		
    }
	
    def destroy = {
    }
	
	void registerMarshallers(){
		
		JSON.registerObjectMarshaller(FailureResponse, FailureResponse.jsonProperties)	
		JSON.registerObjectMarshaller(PagedResponse,PagedResponse.jsonProperties)
		JSON.registerObjectMarshaller(SingleResponse,SingleResponse.jsonProperties)
				
		JSON.registerObjectMarshaller(User,User.jsonProperties) 
		JSON.registerObjectMarshaller(Place,Place.jsonProperties) 
	}
}
