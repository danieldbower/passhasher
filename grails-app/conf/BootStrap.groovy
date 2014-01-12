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
