import grails.converters.JSON

import com.bowerstudios.passhasher.Place
import com.bowerstudios.passhasher.User
import com.bowerstudios.passhasher.json.FailureResponse
import com.bowerstudios.passhasher.json.PagedResponse
import com.bowerstudios.passhasher.json.SingleResponse

class BootStrap {

    def init = { servletContext ->
		User user1 = new User(name:"daniel", username:"daniel", password:"daniel")
		user1.save();
		
		User user2 = new User(name:"ariel", username:"ariel", password:"ariel")
		user2.save();
		
		User user3 = new User(name:"david", username:"david", password:"david")
		user3.save();
		
		User user4 = new User(name:"samuel", username:"samuel", password:"samuel")
		user4.save();
		
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
