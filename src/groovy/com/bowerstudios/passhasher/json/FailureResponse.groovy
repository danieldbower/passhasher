package com.bowerstudios.passhasher.json

class FailureResponse {
	boolean success
	String message
	
	public FailureResponse(String message){
		success = false
		this.message = message
	}
	
	public static jsonProperties = { FailureResponse resp ->
		['success': resp.success, 'message': resp.message]
	}

}
